
package com.sedback.SEDBack.Mappers;

import com.sedback.SEDBack.Dtos.DetalleEvaluacionComparacionCompetenciasDto;
import com.sedback.SEDBack.Modelo.Competencia;
import com.sedback.SEDBack.Modelo.DetalleEvaluacion;
import java.util.ArrayList;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DetalleEvaluacionComparacionCompetenciasDtoMapper {
    DetalleEvaluacionComparacionCompetenciasDtoMapper INSTANCE = Mappers.getMapper(DetalleEvaluacionComparacionCompetenciasDtoMapper.class);
    
    @Mapping(expression = "java(de.getId())", target = "id")
    @Mapping(expression = "java(de.getEvaluado().getNombreCompleto())", target = "nombreEvaluado")
    @Mapping(expression = "java(comp.getNombre())", target = "nombreCompetencia")
    @Mapping(expression = "java(de.getResultadoDeCompetencia(comp))", target = "resultadoCompetencia")
    DetalleEvaluacionComparacionCompetenciasDto toDetalleEvaluacionComparacionCompetenciasDto(DetalleEvaluacion de,Competencia comp);
    
    default List<DetalleEvaluacionComparacionCompetenciasDto> toDetalleEvaluacionComparacionCompetenciasDtoList(List<DetalleEvaluacion> de,Competencia comp){
        List<DetalleEvaluacionComparacionCompetenciasDto> detalleEvaluacionComparacionCompetenciasDtoList = new ArrayList<>();
        for(DetalleEvaluacion x : de){
            detalleEvaluacionComparacionCompetenciasDtoList.add(this.toDetalleEvaluacionComparacionCompetenciasDto(x,comp));
        }
        return detalleEvaluacionComparacionCompetenciasDtoList;
    }
}
