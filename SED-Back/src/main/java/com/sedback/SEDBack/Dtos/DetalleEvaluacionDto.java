package com.sedback.SEDBack.Dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sedback.SEDBack.Modelo.Resultado;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Data
public class DetalleEvaluacionDto {
    private Long id;
    
    private String evaluado;
    
    private String evaluador;
    
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate fechaRealizacion;
    
    private List<Resultado> resultados;
}
