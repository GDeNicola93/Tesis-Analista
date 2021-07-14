package com.sedback.SEDBack.Persistencia;

import com.sedback.SEDBack.Modelo.Evaluacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluacionRepositorio extends JpaRepository<Evaluacion,Long> {
    
}
