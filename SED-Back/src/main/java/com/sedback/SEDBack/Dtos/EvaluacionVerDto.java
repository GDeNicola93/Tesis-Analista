package com.sedback.SEDBack.Dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.sedback.SEDBack.Modelo.Empleado;
import com.sedback.SEDBack.Modelo.Estado;
import com.sedback.SEDBack.Views.Views;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class EvaluacionVerDto {
    private Long id;
    
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate fechaInicioEvaluacion;
    
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate fechaFinEvaluacion;
    
    @JsonFormat(pattern="dd-MM-yyyy HH:mm")
    private LocalDateTime fechaHoraCreacion;
    
    @JsonFormat(pattern="MMMM YYYY")
    private LocalDate periodoInicio;
    
    @JsonFormat(pattern="MMMM YYYY")
    private LocalDate periodoFin;
    
    private Estado estado;
    
    private Integer idEvaluador;
    
    private String nombreEvaluador;
    
    private Integer puntajeMinAprobacion;
    
    private Integer cantidadEmpleadosAEvaluar;
    
    private Integer cantidadEmpleadosEvaluados;
    
    private Integer porcentajeEvaluados;
    
    private Integer porcentajeNoEvaluados;
    
    private Integer restantesAEvaluar;
    
    private String especificacionPuesto;
    
    private String sucursal;
}
