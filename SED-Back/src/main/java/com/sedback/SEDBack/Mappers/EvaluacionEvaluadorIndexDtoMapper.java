package com.sedback.SEDBack.Mappers;

import com.sedback.SEDBack.Dtos.EvaluacionEvaluadorIndexDto;
import com.sedback.SEDBack.Modelo.Evaluacion;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper
public interface EvaluacionEvaluadorIndexDtoMapper {
    EvaluacionEvaluadorIndexDtoMapper INSTANCE = Mappers.getMapper(EvaluacionEvaluadorIndexDtoMapper.class);
    
    EvaluacionEvaluadorIndexDto evaluacionToEvaluacionEvaluadorIndexDto(Evaluacion evaluacion);
    
    default Page<EvaluacionEvaluadorIndexDto> toEvaluacionEvaluadorIndexDtoPage(Page<Evaluacion> evaluacionesPage){
        Page<EvaluacionEvaluadorIndexDto> evaluacionesEvaluadorIndexDto = evaluacionesPage.map(this::evaluacionToEvaluacionEvaluadorIndexDto);
        return evaluacionesEvaluadorIndexDto;
    }
}
