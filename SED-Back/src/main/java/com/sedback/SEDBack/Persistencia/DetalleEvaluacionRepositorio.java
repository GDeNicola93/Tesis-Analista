package com.sedback.SEDBack.Persistencia;

import com.sedback.SEDBack.Modelo.DetalleEvaluacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleEvaluacionRepositorio extends JpaRepository<DetalleEvaluacion,Long> {
    
    @Query(value = "SELECT de FROM DetalleEvaluacion de where de.evaluado.id = ?1")
    Page<DetalleEvaluacion> getDetalleEvaluacionByEvaluado(Integer id_empleado,Pageable pagina);
}
