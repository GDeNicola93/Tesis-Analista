package com.sedback.SEDBack.Mappers;

import com.sedback.SEDBack.Dtos.EspecificacionDePuestoIndexDto;
import com.sedback.SEDBack.Dtos.EspecificacionDePuestoVerDto;
import com.sedback.SEDBack.Modelo.EspecificacionDePuesto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper
public interface EspecificacionDePuestoDtosMapper {
    EspecificacionDePuestoDtosMapper INSTANCE = Mappers.getMapper(EspecificacionDePuestoDtosMapper.class);
    
    
    @Mapping(expression = "java(edp.getId())", target = "idEspecificacionDePuesto")
    @Mapping(expression = "java(edp.getPuesto().getNombrePuesto())", target = "nombrePuesto")  
    @Mapping(expression = "java(edp.getSucursal().getNombre())", target = "sucursalNombre")        
    EspecificacionDePuestoIndexDto toEspecificacionDePuestoIndexDto(EspecificacionDePuesto edp);
    
    default Page<EspecificacionDePuestoIndexDto> toEspecificacionDePuestoIndexDtoPage(Page<EspecificacionDePuesto> edpPage){
        Page<EspecificacionDePuestoIndexDto> especificacionDePuestosIndexDtoPage = edpPage.map(this::toEspecificacionDePuestoIndexDto);
        return especificacionDePuestosIndexDtoPage;
    }
    
    @Mapping(expression = "java(edp.getId())", target = "idEspecificacionDePuesto")
    @Mapping(expression = "java(edp.getPuesto().getNombrePuesto())", target = "nombrePuesto")
    @Mapping(expression = "java(edp.getSucursal().getNombre())", target = "sucursalNombre")
    @Mapping(expression = "java(edp.getPuesto().getArea().getNombre())", target = "areaNombre")    
    EspecificacionDePuestoVerDto toEspecificacionDePuestoVerDto(EspecificacionDePuesto edp);
}
