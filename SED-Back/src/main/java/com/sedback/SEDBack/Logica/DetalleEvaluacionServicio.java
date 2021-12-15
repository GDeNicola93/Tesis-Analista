package com.sedback.SEDBack.Logica;

import com.sedback.SEDBack.Dtos.DetalleEvaluacionComparacionCompetenciasDto;
import com.sedback.SEDBack.Dtos.DetalleEvaluacionVersusReporteDto;
import com.sedback.SEDBack.Dtos.EvaluarIndexDto;
import com.sedback.SEDBack.Dtos.MisEvaluacionesDto;
import com.sedback.SEDBack.Excepciones.PermissionException;
import com.sedback.SEDBack.Mappers.DetalleEvaluacionComparacionCompetenciasDtoMapper;
import com.sedback.SEDBack.Mappers.EvaluarIndexDtoMapper;
import com.sedback.SEDBack.Modelo.Competencia;
import com.sedback.SEDBack.Modelo.DetalleEvaluacion;
import com.sedback.SEDBack.Modelo.Empleado;
import com.sedback.SEDBack.Modelo.Evaluacion;
import com.sedback.SEDBack.Modelo.Usuario;
import com.sedback.SEDBack.Persistencia.DetalleEvaluacionRepositorio;
import com.sedback.SEDBack.Persistencia.EvaluacionRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DetalleEvaluacionServicio {
    @Autowired
    private DetalleEvaluacionRepositorio repositorio;
    
    @Autowired
    private UsuarioServicio usuarioServicio;
    
    @Autowired
    private EvaluacionRepositorio evaluacionRepositorio;
    
     public void guardar(Evaluacion ev,List<Empleado> empleadosAEvaluar){
        List<DetalleEvaluacion> detalles = ev.crearDetallesEvaluacion(empleadosAEvaluar);
        for(DetalleEvaluacion de : detalles){
            repositorio.save(de);
        }
    }
     
    public void guardar(DetalleEvaluacion detalleEvaluacion){
        repositorio.save(detalleEvaluacion);
    }
    
    public Optional<DetalleEvaluacion> findById(Long id){
        return repositorio.findById(id);
    }
    
    public Page<MisEvaluacionesDto> getMisEvaluaciones(Long idUserLogeado,Pageable page){
        Empleado empLogeado = usuarioServicio.getDatosUsuarioLogeado(idUserLogeado).getBody().getEmpleado();
        return repositorio.getDetalleEvaluacionByEvaluado(empLogeado.getId(),page);
    }
    
    public Page<EvaluarIndexDto> getDetallesEvaluacionByIdEvaluacion(Long id_evaluacion,Long idUserLogeado,Pageable page){
        Evaluacion evaluacion = evaluacionRepositorio.findById(id_evaluacion).get();
        Usuario userLogeado = usuarioServicio.getDatosUsuarioLogeado(idUserLogeado).getBody();
        
        if(evaluacion.getEstado().getId() == 3){
            throw new PermissionException("La evaluación esta cancelada por lo que no es posible ver sus detalles.");
        }
        if(userLogeado.esAdministrador()){
            return EvaluarIndexDtoMapper.INSTANCE.toEvaluarIndexDtoPage(repositorio.getDetallesEvaluacionByIdEvaluacion(id_evaluacion,page));
        }
        
        if(userLogeado.esEvaluador()){
            if(evaluacion.esDeEvaluador(userLogeado.getEmpleado())){
                return EvaluarIndexDtoMapper.INSTANCE.toEvaluarIndexDtoPage(repositorio.getDetallesEvaluacionByIdEvaluacion(id_evaluacion,page));
            }
        }
        throw new PermissionException("No puede ver detalles de esta evaluación ya que no esta asignada a su usuario.");
    }
    
    public DetalleEvaluacionVersusReporteDto getDetalleEvaluacionByIdParaVersusReporte(Long id_detalle_evaluacion){
        DetalleEvaluacionVersusReporteDto detalle = repositorio.getDetalleEvaluacionByIdParaVersusReporte(id_detalle_evaluacion).get();
        return detalle;
    }
    
    public List<DetalleEvaluacionComparacionCompetenciasDto> getDetalleEvaluacionByIdEvaluacionParaComparacionReporte(Long idEvaluacion,Competencia comp){
        return DetalleEvaluacionComparacionCompetenciasDtoMapper.INSTANCE.toDetalleEvaluacionComparacionCompetenciasDtoList(repositorio.getDetalleEvaluacionByIdEvaluacionParaComparacionReporte(idEvaluacion),comp);
    }
}
