package com.sedback.SEDBack.Logica;

import com.sedback.SEDBack.Dtos.MisEvaluacionesDto;
import com.sedback.SEDBack.Mappers.MisEvaluacionDtoMapper;
import com.sedback.SEDBack.Modelo.DetalleEvaluacion;
import com.sedback.SEDBack.Modelo.Empleado;
import com.sedback.SEDBack.Modelo.Evaluacion;
import com.sedback.SEDBack.Persistencia.DetalleEvaluacionRepositorio;
import com.sedback.SEDBack.Persistencia.EspecificacionDePuestoRepositorio;
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
    
     public void guardar(Evaluacion ev,List<Empleado> empleadosAEvaluar){
        List<DetalleEvaluacion> detalles = ev.crearDetallesEvaluacion(empleadosAEvaluar);
        for(DetalleEvaluacion de : detalles){
            repositorio.save(de);
        }
    }
    
    public Optional<DetalleEvaluacion> findById(Long id){
        return repositorio.findById(id);
    }
    
    public Page<MisEvaluacionesDto> getMisEvaluaciones(Long idUserLogeado,Pageable page){
        Empleado empLogeado = usuarioServicio.getDatosUsuarioLogeado(idUserLogeado).getBody().getEmpleado();
        return MisEvaluacionDtoMapper.INSTANCE.toMisEvaluacionesDtoPage(repositorio.getDetalleEvaluacionByEvaluado(empLogeado.getId(),page));
    }
}
