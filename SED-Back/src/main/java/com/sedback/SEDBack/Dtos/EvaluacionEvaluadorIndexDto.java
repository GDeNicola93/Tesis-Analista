package com.sedback.SEDBack.Dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sedback.SEDBack.Modelo.Estado;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class EvaluacionEvaluadorIndexDto {
    private Long id;
    
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate fechaInicioEvaluacion;
    
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate fechaFinEvaluacion;
    
    @JsonFormat(pattern="dd-MM-yyyy HH:mm")
    private LocalDateTime fechaHoraCreacion;
    
    private Estado estado;
}
