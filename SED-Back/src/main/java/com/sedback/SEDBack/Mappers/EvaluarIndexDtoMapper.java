package com.sedback.SEDBack.Mappers;

import com.sedback.SEDBack.Dtos.EvaluarIndexDto;
import com.sedback.SEDBack.Modelo.DetalleEvaluacion;
import java.util.ArrayList;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper
public interface EvaluarIndexDtoMapper {
    EvaluarIndexDtoMapper INSTANCE = Mappers.getMapper(EvaluarIndexDtoMapper.class);
    
    @Mapping(expression = "java(de.getId())", target = "idDetalleEvaluacion")
    @Mapping(expression = "java(de.getEvaluado().getNombreCompleto())", target = "empleadoAEvaluar")
    @Mapping(expression = "java(de.getAprobado())", target = "aprobado")
    EvaluarIndexDto toEvaluarIndexDto(DetalleEvaluacion de);
    
    default List<EvaluarIndexDto> toEvaluarIndexDtoList(List<DetalleEvaluacion> de){
        List<EvaluarIndexDto> evaluarIndexDtoList = new ArrayList<>();
        for(DetalleEvaluacion x : de){
            evaluarIndexDtoList.add(this.toEvaluarIndexDto(x));
        }
        return evaluarIndexDtoList;
    }
    
    default Page<EvaluarIndexDto> toEvaluarIndexDtoPage(Page<DetalleEvaluacion> de){
        Page<EvaluarIndexDto> evaluarIndexDtoPage = de.map(this::toEvaluarIndexDto);
        return evaluarIndexDtoPage;
    }
    
}
