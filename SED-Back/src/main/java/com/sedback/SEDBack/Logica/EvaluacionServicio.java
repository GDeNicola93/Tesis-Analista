package com.sedback.SEDBack.Logica;

import com.sedback.SEDBack.Dtos.EvaluacionEvaluadorIndexDto;
import com.sedback.SEDBack.Dtos.EvaluacionIndexDto;
import com.sedback.SEDBack.Dtos.EvaluacionVerDto;
import com.sedback.SEDBack.Dtos.HttpMensaje;
import com.sedback.SEDBack.Dtos.NuevaEvaluacionDto;
import com.sedback.SEDBack.Mappers.EvaluacionEvaluadorIndexDtoMapper;
import com.sedback.SEDBack.Mappers.EvaluacionIndexDtoMapper;
import com.sedback.SEDBack.Mappers.EvaluacionVerDtoMapper;
import com.sedback.SEDBack.Modelo.Empleado;
import com.sedback.SEDBack.Modelo.Estado;
import com.sedback.SEDBack.Modelo.Evaluacion;
import com.sedback.SEDBack.Modelo.Usuario;
import com.sedback.SEDBack.Persistencia.EspecificacionDePuestoRepositorio;
import com.sedback.SEDBack.Persistencia.EstadoRepositorio;
import com.sedback.SEDBack.Persistencia.EvaluacionRepositorio;
import com.sedback.SEDBack.Persistencia.UsuarioRepositorio;
import com.sedback.SEDBack.Seguridad.JWT.JwtProvider;
import java.time.LocalDateTime;
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
    private EspecificacionDePuestoRepositorio especificacionPuestoRepositorio;
    
    @Autowired
    private EstadoRepositorio estadoRepositorio;
    
    @Autowired
    private DetalleEvaluacionServicio detalleEvaluacionServicio;
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    @Autowired
    JwtProvider jwtProvider;
    
    public ResponseEntity<HttpMensaje> guardar(NuevaEvaluacionDto nuevaEvaluacion){
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
        detalleEvaluacionServicio.guardar(evaluacion.crearDetallesEvaluacion(empleadosAEvaluar));
        evaluacionRepositorio.save(evaluacion);
        return ResponseEntity.ok().body(new HttpMensaje("¡Evaluación guardada existosamente!"));
    }
    
    public ResponseEntity<Page<EvaluacionIndexDto>> getEvaluaciones(Pageable page){
        return ResponseEntity.ok().body(EvaluacionIndexDtoMapper.INSTANCE.toEmpleadoIndexDtoPage(evaluacionRepositorio.findAll(page)));
    }
    
    public ResponseEntity<EvaluacionVerDto> getEvaluacionById(Long id,String token){
        Evaluacion evaluacionAMostrar = evaluacionRepositorio.findById(id).get();
        Usuario usuarioLogeado = usuarioRepositorio.findById(jwtProvider.getIdUserFromToken(token)).get();
        if(usuarioLogeado.esAdministrador()){
           return ResponseEntity.ok().body(EvaluacionVerDtoMapper.INSTANCE.evaluacionToEvaluacionVerDto(evaluacionAMostrar)); 
        }
        if(evaluacionAMostrar.esDeEvaluador(usuarioLogeado.getEmpleado())){
            return ResponseEntity.ok().body(EvaluacionVerDtoMapper.INSTANCE.evaluacionToEvaluacionVerDto(evaluacionRepositorio.findById(id).get()));
        }else{
            return ResponseEntity.badRequest().body(null);
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
    
    public ResponseEntity<Page<EvaluacionEvaluadorIndexDto>> getEvaluacionesEvaluadorLogeado(String token,Pageable page){
        Usuario userLogeado = usuarioRepositorio.findById(jwtProvider.getIdUserFromToken(token)).get();
        return ResponseEntity.ok().body(EvaluacionEvaluadorIndexDtoMapper.INSTANCE.toEvaluacionEvaluadorIndexDtoPage(evaluacionRepositorio.getEvaluacionesEvaluador(userLogeado.getEmpleado().getId(),page)));
    }
}
