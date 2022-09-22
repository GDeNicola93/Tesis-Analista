package com.sedback.SEDBack.Dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sedback.SEDBack.Modelo.Estado;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Value;

public interface EvaluacionEvaluadorIndexDto {
    Long getId();
    
    @JsonFormat(pattern="dd-MM-yyyy")
    LocalDate getFechaInicioEvaluacion();
    
    @JsonFormat(pattern="dd-MM-yyyy")
    LocalDate getFechaFinEvaluacion();
    
    @JsonFormat(pattern="MMMM YYYY")
    LocalDate getPeriodoInicio();
    
    @JsonFormat(pattern="MMMM YYYY")
    LocalDate getPeriodoFin();
    
    @Value("#{target.plantillaEvaluacion.especificacionDePuesto.puesto.nombrePuesto + ' (' + target.plantillaEvaluacion.especificacionDePuesto.sucursal.nombre + ')'}")
    String getPuestoTrabajo();
    
    Estado getEstado();
    
    boolean getEstaParaEvaluar();
}
