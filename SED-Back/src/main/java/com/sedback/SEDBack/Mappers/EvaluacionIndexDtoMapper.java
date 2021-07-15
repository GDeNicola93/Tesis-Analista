package com.sedback.SEDBack.Mappers;

import com.sedback.SEDBack.Dtos.EvaluacionIndexDto;
import com.sedback.SEDBack.Modelo.Evaluacion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper
public interface EvaluacionIndexDtoMapper {
    EvaluacionIndexDtoMapper INSTANCE = Mappers.getMapper(EvaluacionIndexDtoMapper.class);
    
    @Mapping(expression = "java(evaluacion.getEvaluador().getNombreCompleto())", target = "evaluador")
    EvaluacionIndexDto evaluacionToEvaluacionIndexDto(Evaluacion evaluacion);
    
    default Page<EvaluacionIndexDto> toEmpleadoIndexDtoPage(Page<Evaluacion> evaluacionesPage){
        Page<EvaluacionIndexDto> evaluacionesIndexDto = evaluacionesPage.map(this::evaluacionToEvaluacionIndexDto);
        return evaluacionesIndexDto;
    }
}
