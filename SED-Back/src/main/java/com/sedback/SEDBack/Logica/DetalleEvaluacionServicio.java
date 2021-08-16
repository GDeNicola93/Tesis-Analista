package com.sedback.SEDBack.Logica;

import com.sedback.SEDBack.Modelo.DetalleEvaluacion;
import com.sedback.SEDBack.Modelo.Empleado;
import com.sedback.SEDBack.Modelo.Evaluacion;
import com.sedback.SEDBack.Persistencia.DetalleEvaluacionRepositorio;
import com.sedback.SEDBack.Persistencia.EspecificacionDePuestoRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleEvaluacionServicio {
    @Autowired
    private DetalleEvaluacionRepositorio repositorio;
    
     public void guardar(Evaluacion ev,List<Empleado> empleadosAEvaluar){
        List<DetalleEvaluacion> detalles = ev.crearDetallesEvaluacion(empleadosAEvaluar);
        for(DetalleEvaluacion de : detalles){
            repositorio.save(de);
        }
    }
    
    public Optional<DetalleEvaluacion> findById(Long id){
        return repositorio.findById(id);
    }
}
