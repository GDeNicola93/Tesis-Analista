package com.sedback.SEDBack.Dtos;

import lombok.Data;

@Data
public class DetalleEvaluacionComparacionCompetenciasDto {
    Long id;
    
    String nombreEvaluado;
    
    String nombreCompetencia;
    
    Integer resultadoCompetencia; //Resultado de la competencia especificada.
}
