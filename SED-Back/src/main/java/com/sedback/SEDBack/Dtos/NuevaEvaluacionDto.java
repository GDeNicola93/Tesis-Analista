package com.sedback.SEDBack.Dtos;

import com.sedback.SEDBack.Modelo.Empleado;
import com.sedback.SEDBack.Modelo.EspecificacionDePuesto;
import com.sedback.SEDBack.Modelo.PlantillaEvaluacion;
import com.sedback.SEDBack.Modelo.Sucursal;
import java.time.LocalDate;
import lombok.Data;

@Data
public class NuevaEvaluacionDto {
    private LocalDate fechaInicioEvaluacion;
    private LocalDate fechaFinEvaluacion;
    private Sucursal sucursal;
    private EspecificacionDePuesto especificacionPuesto;
    private PlantillaEvaluacion plantillaEvaluacion;
    private Empleado empleadoEvaluador;
    private Integer puntajeMinAprobacion;
}
