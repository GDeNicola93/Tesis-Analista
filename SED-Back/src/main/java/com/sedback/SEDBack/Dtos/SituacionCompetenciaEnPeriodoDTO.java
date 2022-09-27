package com.sedback.SEDBack.Dtos;

import com.sedback.SEDBack.Modelo.Competencia;
import java.time.LocalDate;
import lombok.Data;

@Data
public class SituacionCompetenciaEnPeriodoDTO {
    private LocalDate periodo;
    
    private Competencia competencia;
}
