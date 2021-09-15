package com.sedback.SEDBack.Logica;

import com.sedback.SEDBack.Dtos.DetalleEvaluacionDto;
import com.sedback.SEDBack.Dtos.EvaluacionEvaluadorIndexDto;
import com.sedback.SEDBack.Dtos.EvaluacionIndexDto;
import com.sedback.SEDBack.Dtos.EvaluacionVerDto;
import com.sedback.SEDBack.Dtos.EvaluarIndexDto;
import com.sedback.SEDBack.Dtos.HttpMensaje;
import com.sedback.SEDBack.Dtos.NuevaEvaluacionDto;
import com.sedback.SEDBack.Excepciones.FueEvaluadoException;
import com.sedback.SEDBack.Excepciones.FueraDeCursoException;
import com.sedback.SEDBack.Excepciones.NotFoundException;
import com.sedback.SEDBack.Excepciones.PermissionException;
import com.sedback.SEDBack.Mappers.DetalleEvaluacionDtoMapper;
import com.sedback.SEDBack.Mappers.EvaluacionEvaluadorIndexDtoMapper;
import com.sedback.SEDBack.Mappers.EvaluacionIndexDtoMapper;
import com.sedback.SEDBack.Mappers.EvaluacionVerDtoMapper;
import com.sedback.SEDBack.Mappers.EvaluarIndexDtoMapper;
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
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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
    JwtProvider jwtProvider;
    
    public String guardar(NuevaEvaluacionDto nuevaEvaluacion){
        List<Empleado> empleadosAEvaluar = especificacionPuestoRepositorio.findById(nuevaEvaluacion.getEspecificacionPuesto().getId()).get().getEmpleados();
        Estado enEspera = estadoRepositorio.findById(1).get(); //1 --> En Espera
        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setFechaInicioEvaluacion(nuevaEvaluacion.getFechaInicioEvaluacion());
        evaluacion.setFechaFinEvaluacion(nuevaEvaluacion.getFechaFinEvaluacion());
        evaluacion.setEstado(enEspera);
        evaluacion.setEvaluador(nuevaEvaluacion.getEmpleadoEvaluador());
        evaluacion.setPlantillaEvaluacion(nuevaEvaluacion.getPlantillaEvaluacion());
        evaluacion.setPuntajeMinAprobacion(nuevaEvaluacion.getPuntajeMinAprobacion());
        evaluacion.setFechaHoraCreacion(LocalDateTime.now());
        evaluacionRepositorio.save(evaluacion);
        detalleEvaluacionServicio.guardar(evaluacion, empleadosAEvaluar);
        return "¡Evaluación registrada existosamente!";
    }
    
    public ResponseEntity<Page<EvaluacionIndexDto>> getEvaluaciones(Pageable page){
        return ResponseEntity.ok().body(EvaluacionIndexDtoMapper.INSTANCE.toEmpleadoIndexDtoPage(evaluacionRepositorio.findAll(page)));
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
    
    
    //Para el evaluador
    
    public ResponseEntity<Page<EvaluacionEvaluadorIndexDto>> getEvaluacionesEvaluadorLogeado(String token,Pageable page){
        Usuario userLogeado = usuarioRepositorio.findById(jwtProvider.getIdUserFromToken(token)).get();
        return ResponseEntity.ok().body(EvaluacionEvaluadorIndexDtoMapper.INSTANCE.toEvaluacionEvaluadorIndexDtoPage(evaluacionRepositorio.getEvaluacionesEvaluador(userLogeado.getEmpleado().getId(),page)));
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
        
        //if(usuarioLogeado.esEmpleado()){
            if(detalleEvaluacionSeleccionado.esDeEvaluado(usuarioLogeado.getEmpleado())){
                return DetalleEvaluacionDtoMapper.INSTANCE.toDetalleEvaluacionDto(detalleEvaluacionSeleccionado);
            }else{
                throw new PermissionException("No puede ver detalles de esta evaluación ya que no esta asignada a su usuario.");
            }
        //}
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
}
