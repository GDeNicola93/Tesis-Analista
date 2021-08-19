package com.sedback.SEDBack.Dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import lombok.Data;

@Data
public class MisEvaluacionesDto {
    private Long idDetalleEvaluacion;
    
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate fechaRealizacion;
    
    private boolean fueEvaluado;
    
    private Integer calificacion;
    
    private boolean aprobado;
}
