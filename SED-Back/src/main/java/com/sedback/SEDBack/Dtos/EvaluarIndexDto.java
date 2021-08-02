package com.sedback.SEDBack.Dtos;

import lombok.Data;

@Data
public class EvaluarIndexDto {
    private Long idDetalleEvaluacion;
    private String empleadoAEvaluar;
    private Integer calificacion;
    private boolean fueEvaluado;
    private boolean aprobado;
}
