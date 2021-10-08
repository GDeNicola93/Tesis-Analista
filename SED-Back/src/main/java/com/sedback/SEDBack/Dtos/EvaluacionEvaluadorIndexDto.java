package com.sedback.SEDBack.Dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sedback.SEDBack.Modelo.Estado;
import java.time.LocalDate;
import java.time.LocalDateTime;


public interface EvaluacionEvaluadorIndexDto {
    Long getId();
    
    @JsonFormat(pattern="dd-MM-yyyy")
    LocalDate getFechaInicioEvaluacion();
    
    @JsonFormat(pattern="dd-MM-yyyy")
    LocalDate getFechaFinEvaluacion();
    
    @JsonFormat(pattern="dd-MM-yyyy HH:mm")
    LocalDateTime getFechaHoraCreacion(); 
    
    Estado getEstado();
    
    boolean getEstaParaEvaluar();
}
