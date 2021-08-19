
package com.sedback.SEDBack.Mappers;

import com.sedback.SEDBack.Dtos.MisEvaluacionesDto;
import com.sedback.SEDBack.Modelo.DetalleEvaluacion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper
public interface MisEvaluacionDtoMapper {
    MisEvaluacionDtoMapper INSTANCE = Mappers.getMapper(MisEvaluacionDtoMapper.class);
    
    @Mapping(expression = "java(de.getId())",target = "idDetalleEvaluacion")        
    MisEvaluacionesDto toMisEvaluacionDto(DetalleEvaluacion de);
    
    default Page<MisEvaluacionesDto> toMisEvaluacionesDtoPage(Page<DetalleEvaluacion> dePage){
        Page<MisEvaluacionesDto> misEvaluacionesDtoPage = dePage.map(this::toMisEvaluacionDto);
        return misEvaluacionesDtoPage;
    }
}
