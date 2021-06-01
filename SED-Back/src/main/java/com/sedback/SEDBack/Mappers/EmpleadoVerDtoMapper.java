package com.sedback.SEDBack.Mappers;

import com.sedback.SEDBack.Dtos.EmpleadoVerDto;
import com.sedback.SEDBack.Modelo.PuestoTrabajo;
import com.sedback.SEDBack.Modelo.Usuario;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmpleadoVerDtoMapper {
    EmpleadoVerDtoMapper INSTANCE = Mappers.getMapper(EmpleadoVerDtoMapper.class);
    
    
    @Mapping(expression = "java(usuario.getEmpleado().getLegajo())", target = "legajo")
    @Mapping(expression = "java(usuario.getEmpleado().getNombreCompleto())", target = "nombreApellido")
    @Mapping(expression = "java(usuario.getEmpleado().getDni())", target = "dni")
    @Mapping(expression = "java(usuario.getEmpleado().getFechaDeNacimientoFormateada())", target = "fechaDeNacimiento")  
    @Mapping(expression = "java(usuario.getEmpleado().getEmail())", target = "email")  
    @Mapping(expression = "java(setpuestoTrabajoToString(usuario.getEmpleado().getPuestosTrabajo()))", target = "puestos")          
    EmpleadoVerDto empleadotoEmpleadoVerDto(Usuario usuario);
    
    default Set<String> setpuestoTrabajoToString(Set<PuestoTrabajo> puestos){ //Devuelve un Set con el nombre del puesto y el nombre de la sucursal del puesto
        Set<String> puestosTrabajoToString = new HashSet<>();
        for (PuestoTrabajo p : puestos) {
            puestosTrabajoToString.add(p.getNombrePuesto() + "(" + p.getSucursal().getNombre() + ")");
        }
        return puestosTrabajoToString;
    }
}
