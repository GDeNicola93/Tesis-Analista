package com.sedback.SEDBack.Persistencia;

import com.sedback.SEDBack.Dtos.DetalleEvaluacionVersusReporteDto;
import com.sedback.SEDBack.Dtos.MisEvaluacionesDto;
import com.sedback.SEDBack.Modelo.Competencia;
import com.sedback.SEDBack.Modelo.DetalleEvaluacion;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleEvaluacionRepositorio extends JpaRepository<DetalleEvaluacion,Long> {
    
    @Query(value = "SELECT de FROM DetalleEvaluacion de where de.evaluado.id = ?1 AND de.evaluacion.estado.id = 4")
    Page<MisEvaluacionesDto> getDetalleEvaluacionByEvaluado(Integer id_empleado,Pageable pagina);
    
    @Query(value = "SELECT de FROM DetalleEvaluacion de where de.evaluacion.id = ?1")
    Page<DetalleEvaluacion> getDetallesEvaluacionByIdEvaluacion(Long id_evaluacion,Pageable pagina);
    
    
    //Las siguientes lineas se usan para los reportes
    
    @Query(value = "SELECT de FROM DetalleEvaluacion de where de.id = ?1")
    Optional<DetalleEvaluacionVersusReporteDto> getDetalleEvaluacionByIdParaVersusReporte(Long id_detalle_evaluacion);
    
    @Query(value = "SELECT de FROM DetalleEvaluacion de WHERE de.evaluacion.id = ?1")
    List<DetalleEvaluacion> getDetalleEvaluacionByIdEvaluacionParaComparacionReporte(Long id_evaluacion);
    
}