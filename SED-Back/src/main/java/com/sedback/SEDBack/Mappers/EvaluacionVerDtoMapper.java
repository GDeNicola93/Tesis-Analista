package com.sedback.SEDBack.Mappers;

import com.sedback.SEDBack.Dtos.EvaluacionVerDto;
import com.sedback.SEDBack.Modelo.DetalleEvaluacion;
import com.sedback.SEDBack.Modelo.Evaluacion;
import java.util.ArrayList;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EvaluacionVerDtoMapper {
    EvaluacionVerDtoMapper INSTANCE = Mappers.getMapper(EvaluacionVerDtoMapper.class);
    
    @Mapping(expression = "java(evaluacion.getEvaluador().getId())", target = "idEvaluador")
    @Mapping(expression = "java(evaluacion.getEvaluador().getNombreCompleto())", target = "nombreEvaluador")
    @Mapping(expression = "java(evaluacion.getPlantillaEvaluacion().getEspecificacionDePuesto().getPuesto().getNombrePuesto())", target = "especificacionPuesto")
    @Mapping(expression = "java(evaluacion.getPlantillaEvaluacion().getEspecificacionDePuesto().getSucursal().getNombre())", target = "sucursal")
    @Mapping(expression = "java(this.detalleEvaluacionToEmpleadosAEvaluar(evaluacion.getDetalleEvaluacion()))", target = "empleadosAEvaluar")
    EvaluacionVerDto evaluacionToEvaluacionVerDto(Evaluacion evaluacion);
    
    
    default List<String> detalleEvaluacionToEmpleadosAEvaluar(List<DetalleEvaluacion> detalleEvaluacion){
        List<String> nombresEmpleados = new ArrayList<>();
        for(DetalleEvaluacion de : detalleEvaluacion){
            nombresEmpleados.add(de.getEvaluado().getNombreCompleto());
        }
        return nombresEmpleados;
    }

}