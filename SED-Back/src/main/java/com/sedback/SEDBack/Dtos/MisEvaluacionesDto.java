package com.sedback.SEDBack.Dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Value;


public interface MisEvaluacionesDto {
    @Value("#{target.id}")
    Long getIdDetalleEvaluacion();
    
    @JsonFormat(pattern="dd-MM-yyyy")
    LocalDate getFechaRealizacion();
    
    Integer getCalificacion();
    
    boolean getAprobado();
}
