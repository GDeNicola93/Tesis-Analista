package com.sedback.SEDBack.Dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;


public interface DetalleEvaluacionVersusReporteDto {
    Long getId();
    
    @Value("#{target.getEvaluado().getNombre() + ' ' + target.getEvaluado().getApellido()}")
    String getNombreApellidoEvaluado();
    
    @JsonFormat(pattern="dd-MM-yyyy HH:mm")
    LocalDateTime getFechaHoraRealizacion();
    
    @JsonFormat(pattern="MMMM YYYY")
    @Value("#{target.getEvaluacion().getPeriodoInicio()}")
    LocalDate getPeriodoInicio();
    
    @JsonFormat(pattern="MMMM YYYY")
    @Value("#{target.getEvaluacion().getPeriodoFin()}")
    LocalDate getPeriodoFin();
    
    boolean getFueEvaluado();
    
    @Value("#{target.getEvaluacion().getEvaluador().getNombre() + ' ' + target.getEvaluacion().getEvaluador().getApellido()}")
    String getNombreApellidoEvaluador();
    
    List<ResultadoVersusReporteDto> getResultados();
}
