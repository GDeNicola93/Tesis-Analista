package com.sedback.SEDBack.Dtos;

import com.sedback.SEDBack.Modelo.Empleado;
import com.sedback.SEDBack.Modelo.EspecificacionDePuesto;
import com.sedback.SEDBack.Modelo.PlantillaEvaluacion;
import com.sedback.SEDBack.Modelo.Sucursal;
import java.time.LocalDate;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Data;

@Data
public class NuevaEvaluacionDto {
    
    @NotNull(message = "La fecha de inicio de evaluación es requerida.")
    @Future(message = "La fecha de inicio de evaluación debe ser posterior a la fecha actual.")
    private LocalDate fechaInicioEvaluacion;
    
    @NotNull(message = "La fecha de fin de evaluación es requerida.")
    private LocalDate fechaFinEvaluacion;
    
    @NotNull(message = "El periodo de inicio a evaluar es requerido.")
    private LocalDate periodoInicio;
    
    @NotNull(message = "El periodo de fin a evaluar es requerido.")
    private LocalDate periodoFin;
    
    @NotNull(message = "Debe seleccionar una sucursal.")
    private Sucursal sucursal;
    
    @NotNull(message = "Debe seleccionar una especificación de puesto valida.")
    private EspecificacionDePuesto especificacionPuesto;
    
    @NotNull(message = "Debe seleccionar una plantilla de evaluación valida.")
    private PlantillaEvaluacion plantillaEvaluacion;
    
    @NotNull(message = "Debe seleccionar un evaluador valido.")
    private Empleado empleadoEvaluador;
    
    @AssertTrue(message="La fecha de fin de evaluación debe ser posterior a la fecha de inicio.")
    public boolean isValidFechaFin(){
        if(fechaFinEvaluacion != null && fechaInicioEvaluacion != null){
            if(fechaFinEvaluacion.isBefore(fechaInicioEvaluacion) || fechaFinEvaluacion.equals(fechaInicioEvaluacion)){
                return false;
            }
        }
        return true;
    }
}
