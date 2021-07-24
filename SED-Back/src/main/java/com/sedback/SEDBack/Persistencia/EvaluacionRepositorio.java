package com.sedback.SEDBack.Persistencia;

import com.sedback.SEDBack.Modelo.Evaluacion;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluacionRepositorio extends JpaRepository<Evaluacion,Long> {
    
    @Query(value = "SELECT ev FROM Evaluacion ev where ev.estado.id = 1")
    List<Evaluacion> getEvaluacionesEnEspera();
    
    @Query(value = "SELECT ev FROM Evaluacion ev where ev.estado.id = 2")
    List<Evaluacion> getEvaluacionesEnCurso();
    
    @Query(value = "SELECT ev FROM Evaluacion ev where ev.evaluador.id = ?1")
    Page<Evaluacion> getEvaluacionesEvaluador(Integer id_empleado,Pageable pagina);
}
