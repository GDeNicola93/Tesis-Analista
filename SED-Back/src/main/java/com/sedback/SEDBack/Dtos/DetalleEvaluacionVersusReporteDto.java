package com.sedback.SEDBack.Dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;


public interface DetalleEvaluacionVersusReporteDto {
    Long getId();
    
    @Value("#{target.getEvaluado().getNombre() + ' ' + target.getEvaluado().getApellido()}")
    String getNombreApellidoEvaluado();
    
    @JsonFormat(pattern="dd-MM-yyyy")
    LocalDate getFechaRealizacion();
    
    boolean getFueEvaluado();
    
    @Value("#{target.getEvaluacion().getEvaluador().getNombre() + ' ' + target.getEvaluacion().getEvaluador().getApellido()}")
    String getNombreApellidoEvaluador();
    
    List<ResultadoVersusReporteDto> getResultados();
}
