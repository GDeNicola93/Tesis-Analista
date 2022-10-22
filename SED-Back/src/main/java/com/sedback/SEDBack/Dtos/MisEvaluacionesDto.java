package com.sedback.SEDBack.Dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Value;


public interface MisEvaluacionesDto {
    @Value("#{target.id}")
    Long getIdDetalleEvaluacion();
    
    @Value("#{target.evaluacion.periodoInicio}")
    @JsonFormat(pattern="MMMM YYYY")
    LocalDate getPeriodoInicio();
    
    @Value("#{target.evaluacion.periodoFin}")
    @JsonFormat(pattern="MMMM YYYY")
    LocalDate getPeriodoFin();
    
    @Value("#{target.evaluacion.estado.nombre}")
    String getEstado();
    
    @Value("#{target.evaluacion.evaluador.getNombreCompleto()}")
    String getEvaluador();
}
