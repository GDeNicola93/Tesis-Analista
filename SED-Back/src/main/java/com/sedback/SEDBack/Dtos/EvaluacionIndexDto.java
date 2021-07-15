package com.sedback.SEDBack.Dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sedback.SEDBack.Modelo.Estado;
import java.time.LocalDate;
import lombok.Data;

@Data
public class EvaluacionIndexDto {
    private Long id;
    
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate fechaInicioEvaluacion;
    
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate fechaFinEvaluacion;
    
    private String evaluador;
    
    private Estado estado;
}
