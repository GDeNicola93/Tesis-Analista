package com.sedback.SEDBack.Dtos;

import org.springframework.beans.factory.annotation.Value;

public interface ResultadoVersusReporteDto {
    Long getId();
    
    @Value("#{target.getDetallePlantilla().getCompetencia().getNombre()}")
    String getComptetencia();
   
    @Value("#{target.getDetallePlantilla().getMinimaValoracionNumerica()}")
    int getMinimaValoracionNumerica();
    
    @Value("#{target.getComportamientoPlantillaSeleccionado().getValoracionNumerica()}")
    int getValoracionNumericaSeleccionada();
}
