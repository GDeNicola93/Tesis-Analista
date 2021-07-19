package com.sedback.SEDBack.Persistencia;

import com.sedback.SEDBack.Modelo.Evaluacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluacionRepositorio extends JpaRepository<Evaluacion,Long> {
    
    @Query(value = "SELECT ev FROM Evaluacion ev where ev.estado.id = 1")
    List<Evaluacion> getEvaluacionesEnEspera();
    
    @Query(value = "SELECT ev FROM Evaluacion ev where ev.estado.id = 2")
    List<Evaluacion> getEvaluacionesEnCurso();
}
