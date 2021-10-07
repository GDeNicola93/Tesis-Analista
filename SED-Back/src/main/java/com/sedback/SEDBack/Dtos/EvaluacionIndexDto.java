package com.sedback.SEDBack.Dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sedback.SEDBack.Modelo.Estado;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Value;

public interface EvaluacionIndexDto {
    Long getId();
    
    @JsonFormat(pattern="dd-MM-yyyy")
    LocalDate getFechaInicioEvaluacion();
    
    @JsonFormat(pattern="dd-MM-yyyy")
    LocalDate getFechaFinEvaluacion();
    
    @Value("#{target.evaluador.nombre + ' ' + target.evaluador.apellido}")
    String getNombreCompletoEvaluador();
    
    Estado getEstado();
    
    boolean getEsCancelable();
}
