package com.sedback.SEDBack.Mappers;

import com.sedback.SEDBack.Dtos.DetalleEvaluacionDto;
import com.sedback.SEDBack.Modelo.DetalleEvaluacion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DetalleEvaluacionDtoMapper {
    DetalleEvaluacionDtoMapper INSTANCE = Mappers.getMapper(DetalleEvaluacionDtoMapper.class);
    
    @Mapping(expression = "java(detalle.getEvaluado().getNombreCompleto())", target = "evaluado")
    @Mapping(expression = "java(detalle.getAprobado(puntajeMinAprobacion))", target = "aprobado")
    DetalleEvaluacionDto toDetalleEvaluacionDto(DetalleEvaluacion detalle,Integer puntajeMinAprobacion);
}
