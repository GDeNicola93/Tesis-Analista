package com.sedback.SEDBack.Persistencia;

import com.sedback.SEDBack.Modelo.DetalleEvaluacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleEvaluacionRepositorio extends JpaRepository<DetalleEvaluacion,Long> {
    
}
