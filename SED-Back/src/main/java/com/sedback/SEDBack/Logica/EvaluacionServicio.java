package com.sedback.SEDBack.Logica;

import com.sedback.SEDBack.Dtos.DetalleEvaluacionDto;
import com.sedback.SEDBack.Dtos.EvaluacionEvaluadorIndexDto;
import com.sedback.SEDBack.Dtos.EvaluacionIndexDto;
import com.sedback.SEDBack.Dtos.EvaluacionVerDto;
import com.sedback.SEDBack.Dtos.EvaluarIndexDto;
import com.sedback.SEDBack.Dtos.HttpMensaje;
import com.sedback.SEDBack.Dtos.NuevaEvaluacionDto;
import com.sedback.SEDBack.Dtos.PuestoTrabajoSituacionCompetenciaDTO;
import com.sedback.SEDBack.Dtos.RespuestaSituacionCompetenciaDTO;
import com.sedback.SEDBack.Dtos.ResultadoDto;
import com.sedback.SEDBack.Dtos.SituacionCompetenciaEnPeriodoDTO;
import com.sedback.SEDBack.Excepciones.BadRequestException;
import com.sedback.SEDBack.Excepciones.FueEvaluadoException;
import com.sedback.SEDBack.Excepciones.FueraDeCursoException;
import com.sedback.SEDBack.Excepciones.PermissionException;
import com.sedback.SEDBack.Mappers.DetalleEvaluacionDtoMapper;
import com.sedback.SEDBack.Mappers.EvaluacionVerDtoMapper;
import com.sedback.SEDBack.Mappers.EvaluarIndexDtoMapper;
import com.sedback.SEDBack.Modelo.Competencia;
import com.sedback.SEDBack.Modelo.DetalleEvaluacion;
import com.sedback.SEDBack.Modelo.Empleado;
import com.sedback.SEDBack.Modelo.Estado;
import com.sedback.SEDBack.Modelo.Evaluacion;
import com.sedback.SEDBack.Modelo.PlantillaEvaluacion;
import com.sedback.SEDBack.Modelo.Usuario;
import com.sedback.SEDBack.Persistencia.EspecificacionDePuestoRepositorio;
import com.sedback.SEDBack.Persistencia.EstadoRepositorio;
import com.sedback.SEDBack.Persistencia.EvaluacionRepositorio;
import com.sedback.SEDBack.Persistencia.UsuarioRepositorio;
import com.sedback.SEDBack.Seguridad.JWT.JwtProvider;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EvaluacionServicio {
    @Autowired
    private EvaluacionRepositorio evaluacionRepositorio;
    
    @Autowired
    private EstadoRepositorio estadoRepositorio;
    
    @Autowired
    private EspecificacionDePuestoRepositorio especificacionPuestoRepositorio;
    
    @Autowired
    private DetalleEvaluacionServicio detalleEvaluacionServicio;
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio; //Esta inyeccion habria que sacarla.
    
    @Autowired
    private UsuarioServicio usuarioServicio;
    
    @Autowired
    private ResultadoServicio resultadoServicio;
    
    @Autowired
    JwtProvider jwtProvider;
    
    public String guardar(NuevaEvaluacionDto nuevaEvaluacion){
        if(!(evaluacionRepositorio.getEvaluacionesDePeriodoYDePuesto(nuevaEvaluacion.getEspecificacionPuesto().getId(),nuevaEvaluacion.getPeriodoInicio(),nuevaEvaluacion.getPeriodoFin()).isEmpty())){
            throw new BadRequestException("Ya existe una evaluacion En Espera o En Curso para el puesto de trabajo seleccionado en el periodo a evaluar ingresado.");
        }
        List<Empleado> empleadosAEvaluar = especificacionPuestoRepositorio.findById(nuevaEvaluacion.getEspecificacionPuesto().getId()).get().getEmpleados();
        Estado enEspera = estadoRepositorio.findById(1).get(); //1 --> En Espera
        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setFechaInicioEvaluacion(nuevaEvaluacion.getFechaInicioEvaluacion());
        evaluacion.setFechaFinEvaluacion(nuevaEvaluacion.getFechaFinEvaluacion());
        evaluacion.setPeriodoInicio(nuevaEvaluacion.getPeriodoInicio());
        evaluacion.setPeriodoFin(nuevaEvaluacion.getPeriodoFin());
        evaluacion.setEstado(enEspera);
        evaluacion.setEvaluador(nuevaEvaluacion.getEmpleadoEvaluador());
        evaluacion.setPlantillaEvaluacion(nuevaEvaluacion.getPlantillaEvaluacion());
        evaluacion.setFechaHoraCreacion(LocalDateTime.now());
        evaluacionRepositorio.save(evaluacion);
        detalleEvaluacionServicio.guardar(evaluacion, empleadosAEvaluar);
        return "¡Evaluación registrada existosamente!";
    }
    
    public ResponseEntity<Page<EvaluacionIndexDto>> getEvaluaciones(Pageable page,String estado,String filtro,String filtroFecha){
        try{
            if(filtroFecha != null && filtroFecha != "undefined"){
                LocalDate fechaDate = LocalDate.parse(filtroFecha);
                return ResponseEntity.ok().body(evaluacionRepositorio.getEvaluacionesConFiltroFecha(page, estado,filtro,fechaDate));
            }
            return ResponseEntity.ok().body(evaluacionRepositorio.getEvaluaciones(page, estado,filtro));
        }catch(Exception e){
            throw new BadRequestException("Error de formateo de fecha. Descripción: "+e.getMessage());
        }
    }
    
    public EvaluacionVerDto getEvaluacionById(Long id,String token){
        Evaluacion evaluacionAMostrar = evaluacionRepositorio.findById(id).get();
        Usuario usuarioLogeado = usuarioRepositorio.findById(jwtProvider.getIdUserFromToken(token)).get();
        if(usuarioLogeado.esAdministrador()){
           return EvaluacionVerDtoMapper.INSTANCE.evaluacionToEvaluacionVerDto(evaluacionAMostrar); 
        }
        if(evaluacionAMostrar.esDeEvaluador(usuarioLogeado.getEmpleado())){
            return EvaluacionVerDtoMapper.INSTANCE.evaluacionToEvaluacionVerDto(evaluacionAMostrar);
        }else{
            throw new PermissionException("La evaluación que intenta visualizar no se encuentra asignada a su usuario.");
        }
    }
    
    public List<Competencia> getCompetenciasEvaluadasEnEvaluaicion(Long idEvaluacion){
        return evaluacionRepositorio.findById(idEvaluacion).get().getCompetenciasEvaluadas();
    }
    
    public ResponseEntity<HttpMensaje> cancelarEvaluacion(Long id_evaluacion){
        Estado estadoCancelado = estadoRepositorio.findById(3).get(); //3 --> Cancelada
        Evaluacion evaluacion = evaluacionRepositorio.findById(id_evaluacion).get();
        if(evaluacion.cancelar(estadoCancelado)){
            evaluacionRepositorio.save(evaluacion);
            return ResponseEntity.ok().body(new HttpMensaje("La evaluación fue cancelada."));
        }else{
            return ResponseEntity.badRequest().body(new HttpMensaje("La evaluación seleccionada no se puede cancelar."));
        }
    }
    
    public List<EvaluacionIndexDto> getEvaluacionesByPlantillaEvaluacion(PlantillaEvaluacion plantilla){
        return this.evaluacionRepositorio.findByPlantillaEvaluacion(plantilla);
    }
    
    
    //Para el evaluador
    
    public ResponseEntity<Page<EvaluacionEvaluadorIndexDto>> getEvaluacionesEvaluadorLogeado(String token,Pageable page){
        Usuario userLogeado = usuarioRepositorio.findById(jwtProvider.getIdUserFromToken(token)).get();
        return ResponseEntity.ok().body(evaluacionRepositorio.getEvaluacionesEvaluador(userLogeado.getEmpleado().getId(),page));
    }
    
    public List<EvaluarIndexDto> getEmpleadosAEvaluarEvaluacion(Long id,Long idUserLogeado){
        Evaluacion evaluacionSeleccionada = evaluacionRepositorio.findById(id).get();
        if(evaluacionSeleccionada.esDeEvaluador(usuarioServicio.getDatosUsuarioLogeado(idUserLogeado).getBody().getEmpleado())){
            if(evaluacionSeleccionada.getEstaParaEvaluar()){
                List<DetalleEvaluacion> detallesEvaluacionSeleccionada = evaluacionSeleccionada.getDetalleEvaluacion();
                return EvaluarIndexDtoMapper.INSTANCE.toEvaluarIndexDtoList(detallesEvaluacionSeleccionada); 
            }else{
                throw new FueraDeCursoException("La evaluación se encuentra en estado "+evaluacionSeleccionada.getEstado().getNombre()+" por lo que no es posible realizar el proceso de evaluación en este momento.");
            }
        }else{
            throw new PermissionException("La evaluación que quiere evaluar no se encuentra asignada a su usuario.");
        }
    }
    
    public DetalleEvaluacionDto getDetalleEvaluacionById(Long idDetalleEvaluacion,Long idUserLogeado){
        DetalleEvaluacion detalleEvaluacionSeleccionado = detalleEvaluacionServicio.findById(idDetalleEvaluacion).get();
        Usuario usuarioLogeado = usuarioServicio.getDatosUsuarioLogeado(idUserLogeado).getBody();
       
        if(usuarioLogeado.esAdministrador()){
            return DetalleEvaluacionDtoMapper.INSTANCE.toDetalleEvaluacionDto(detalleEvaluacionSeleccionado);
        }
        
        if(usuarioLogeado.esEvaluador()){
            if(detalleEvaluacionSeleccionado.getEvaluacion().esDeEvaluador(usuarioLogeado.getEmpleado())){
                return DetalleEvaluacionDtoMapper.INSTANCE.toDetalleEvaluacionDto(detalleEvaluacionSeleccionado);
            }else{
                throw new PermissionException("No puede ver detalles de esta evaluación ya que no esta asignada a su usuario.");
            }
        } 
        
        if(detalleEvaluacionSeleccionado.esDeEvaluado(usuarioLogeado.getEmpleado())){
            if(detalleEvaluacionSeleccionado.getEvaluacion().getEstado().getId() == 3){
                throw new PermissionException("La evaluación esta Cancelada por lo que no se puede ver el este detalle.");
            }
            return DetalleEvaluacionDtoMapper.INSTANCE.toDetalleEvaluacionDto(detalleEvaluacionSeleccionado);
        }else{
            throw new PermissionException("No puede ver detalles de esta evaluación ya que no esta asignada a su usuario.");
        } 
    }
    
    public PlantillaEvaluacion getEvaluarEmpleado(Long id_detalle_evaluacion,Long idUserLogeado){
        DetalleEvaluacion de = detalleEvaluacionServicio.findById(id_detalle_evaluacion).get();
        Usuario usuarioLogeado = usuarioServicio.getDatosUsuarioLogeado(idUserLogeado).getBody();
        if(de.esDeEvaluador(usuarioLogeado.getEmpleado())){
            if(de.getFueEvaluado()){
                throw new FueEvaluadoException("El empleado que intenta evaluar ya ha sido evaluado.");
            }
            return de.getEvaluacion().getPlantillaEvaluacion();
        }else{
            throw new PermissionException("La evaluación que intenta realizar no esta asignada a su usuario.");
        }
    }
    
    public String guardarResultados(List<ResultadoDto> resultadosDto){
        if(resultadosDto.isEmpty()){
            throw new BadRequestException("Faltan competencias por evaluar.");
        }
        DetalleEvaluacion detalleEvaluacionAEvaluar = detalleEvaluacionServicio.findById(resultadosDto.get(0).getIdDetalleEvaluacion()).get();
        if(detalleEvaluacionAEvaluar.getEvaluacion().getPlantillaEvaluacion().contarCompetenciasAEvaluar() != resultadosDto.size()){
            throw new BadRequestException("Faltan competencias por evaluar.");
        }
        
        //Creo los resultados para el detalleEvaluacionAEvaluar
        for(ResultadoDto x : resultadosDto){
            detalleEvaluacionAEvaluar.crearResultado(x);
        }
        
//        No hace falta guardar los resultados antes de guardar
//        el detalle de evaluacion ya que en este el atributo resultados
//        esta configurado como cascade = { CascadeType.ALL }
        
        detalleEvaluacionAEvaluar.setFechaHoraRealizacion(LocalDateTime.now());
        
        //Si ya fue el ultimo resultado a cargar Finalizo la evaluación.
        if(detalleEvaluacionAEvaluar.getEvaluacion().terminoDeEvaluar()){
            Estado estadoFinalziada = estadoRepositorio.findById(4).get(); //4 --> Finalizada
            detalleEvaluacionAEvaluar.getEvaluacion().setEstado(estadoFinalziada);
        }
        
        this.detalleEvaluacionServicio.guardar(detalleEvaluacionAEvaluar);
        return "Resultado de evaluación registrado exitosamente!";
    }
    
    public RespuestaSituacionCompetenciaDTO situacionCompetenciaEnPeriodo(SituacionCompetenciaEnPeriodoDTO scp){
//        Busco las evaluaciones que estan en periodo ingresado por el user
        List<Evaluacion> evaluaciones = this.evaluacionRepositorio.getEvaluacionesParaSituacionCompetenciaEnPeriodo(scp.getPeriodo());
        
//        Valido que existan resultados caso contrario lanzo excepcion
        if(evaluaciones.isEmpty()){
           throw new BadRequestException("No se encontrarón evaluaciones que coincidan con los parametros seleccionados."); 
        }
        RespuestaSituacionCompetenciaDTO rta = new RespuestaSituacionCompetenciaDTO();
        
//        int cantSuperaronOalcanzaronMin = 0;
//        int cantidadNoAlcanzaronMinimoRequerido = 0;
//        int cantNoEvaluados = 0;
        
        for(Evaluacion ev : evaluaciones){
            if(ev.seEvaluaCompetencia(scp.getCompetencia())){
//                cantSuperaronOalcanzaronMin = cantSuperaronOalcanzaronMin + ev.cantidadSuperaronMinimoRequerido(scp.getCompetencia());
//                cantidadNoAlcanzaronMinimoRequerido = cantidadNoAlcanzaronMinimoRequerido + ev.cantidadNoAlcanzaronMinimoRequerido(scp.getCompetencia());
//                cantNoEvaluados = cantNoEvaluados + ev.cantidadNoEvaluados();
                PuestoTrabajoSituacionCompetenciaDTO puesto = new PuestoTrabajoSituacionCompetenciaDTO();
                puesto.setNombrePuesto(ev.getPlantillaEvaluacion().getEspecificacionDePuesto().getPuesto().getNombrePuesto());
                puesto.setSucursal(ev.getPlantillaEvaluacion().getEspecificacionDePuesto().getSucursal().getNombre());
                puesto.setCantSuperaronOalcanzaronMin(ev.cantidadSuperaronMinimoRequerido(scp.getCompetencia()));
                puesto.setCantidadNoAlcanzaronMinimoRequerido(ev.cantidadNoAlcanzaronMinimoRequerido(scp.getCompetencia()));
                puesto.setCantNoEvaluados(ev.cantidadNoEvaluados());
                rta.addPuestoTrabajo(puesto);
            }
        }
//        RespuestaSituacionCompetenciaDTO rta = new RespuestaSituacionCompetenciaDTO();
//        rta.setCantSuperaronOalcanzaronMin(cantSuperaronOalcanzaronMin);
//        rta.setCantidadNoAlcanzaronMinimoRequerido(cantidadNoAlcanzaronMinimoRequerido);
//        rta.setCantNoEvaluados(cantNoEvaluados);
        return rta;
    }
}