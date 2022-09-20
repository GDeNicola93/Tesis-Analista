package com.sedback.SEDBack.TareasAutomaticas;

import com.sedback.SEDBack.Modelo.Estado;
import com.sedback.SEDBack.Modelo.Evaluacion;
import com.sedback.SEDBack.Persistencia.EstadoRepositorio;
import com.sedback.SEDBack.Persistencia.EvaluacionRepositorio;
import java.time.LocalDate;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EstadoEvaluaciones {
    @Autowired
    private EvaluacionRepositorio evaluacionRepositorio;
    
    @Autowired
    private EstadoRepositorio estadoRepositorio;
    
    @Scheduled(cron = "0 0 1 1/1 * ?") //Se ejecuta todos los dias a la 01:00 AM
    @PostConstruct
    public void actualizarEstados(){
        log.info("Actualizando estados de evaluaciones...");
        this.actualizarEstadoEnEsperaAEnCurso(LocalDate.now());
        this.actualizarEstadoEnCursoAFinalizada(LocalDate.now());
    }
    
    private void actualizarEstadoEnEsperaAEnCurso(LocalDate fechaActual){
        Estado enCurso = estadoRepositorio.findById(2).get();
        List<Evaluacion> evaluacionesEnEspera = evaluacionRepositorio.getEvaluacionesEnEspera();
        
        for(Evaluacion ev : evaluacionesEnEspera){
            if(ev.enCurso(fechaActual, enCurso)){
                log.info("Se puso en curso evaluación id " + ev.getId());
                evaluacionRepositorio.save(ev);
            }
        }
    }
    
    private void actualizarEstadoEnCursoAFinalizada(LocalDate fechaActual){
        Estado finalizada = estadoRepositorio.findById(4).get();
        List<Evaluacion> evaluacionesEnCurso = evaluacionRepositorio.getEvaluacionesEnCurso();
       
        for(Evaluacion ev : evaluacionesEnCurso){
            if(ev.finalizar(fechaActual, finalizada)){
                log.info("Se finalizo evaluación id " + ev.getId());
                evaluacionRepositorio.save(ev);
            }
        }
        
    }
}
