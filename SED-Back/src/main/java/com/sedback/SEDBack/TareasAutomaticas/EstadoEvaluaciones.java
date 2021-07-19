package com.sedback.SEDBack.TareasAutomaticas;

import com.sedback.SEDBack.Modelo.Estado;
import com.sedback.SEDBack.Modelo.Evaluacion;
import com.sedback.SEDBack.Persistencia.EstadoRepositorio;
import com.sedback.SEDBack.Persistencia.EvaluacionRepositorio;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EstadoEvaluaciones {
    @Autowired
    private EvaluacionRepositorio evaluacionRepositorio;
    
    @Autowired
    private EstadoRepositorio estadoRepositorio;
    
    @Scheduled(cron = "0 0 1 1/1 * ?") //Se ejecuta todos los dias a la 01:00 AM
    public void actualizarEstados(){
        this.actualizarEstadoEnEsperaAEnCurso(LocalDate.now());
        this.actualizarEstadoEnCursoAFinalizada(LocalDate.now());
    }
    
    private void actualizarEstadoEnEsperaAEnCurso(LocalDate fechaActual){
        Estado enCurso = estadoRepositorio.findById(2).get();
        List<Evaluacion> evaluacionesEnEspera = evaluacionRepositorio.getEvaluacionesEnEspera();
        
        for(Evaluacion ev : evaluacionesEnEspera){
            if(ev.enCurso(fechaActual, enCurso)){
                evaluacionRepositorio.save(ev);
            }
        }
    }
    
    private void actualizarEstadoEnCursoAFinalizada(LocalDate fechaActual){
        Estado finalizada = estadoRepositorio.findById(4).get();
        List<Evaluacion> evaluacionesEnCurso = evaluacionRepositorio.getEvaluacionesEnCurso();
        
        for(Evaluacion ev : evaluacionesEnCurso){
            if(ev.finalizar(fechaActual, finalizada)){
                evaluacionRepositorio.save(ev);
            }
        }
        
    }
}
