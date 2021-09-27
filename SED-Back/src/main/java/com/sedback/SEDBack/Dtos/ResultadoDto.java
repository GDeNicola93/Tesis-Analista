package com.sedback.SEDBack.Dtos;

import com.sedback.SEDBack.Modelo.ComportamientoPlantilla;
import com.sedback.SEDBack.Modelo.DetallePlantilla;
import lombok.Data;

@Data
public class ResultadoDto {
    private Long idDetalleEvaluacion;
    private DetallePlantilla detallePlantilla;
    private ComportamientoPlantilla comportamientoPlantillaSeleccionado;
}
