
package com.sedback.SEDBack.Mappers;

import com.sedback.SEDBack.Dtos.EmpleadoIndexDto;
import com.sedback.SEDBack.Modelo.EspecificacionDePuesto;
import com.sedback.SEDBack.Modelo.Usuario;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper
public interface EmpleadoIndexDtoMapper {
    EmpleadoIndexDtoMapper INSTANCE = Mappers.getMapper(EmpleadoIndexDtoMapper.class);
    
    @Mapping(expression = "java(usuario.getEmpleado().getId())", target = "idEmpleado")
    @Mapping(expression = "java(usuario.getEmpleado().getLegajo())", target = "legajo")
    @Mapping(expression = "java(usuario.getEmpleado().getNombreCompleto())", target = "nombreApellido")
    @Mapping(expression = "java(usuario.isHabilitado())", target = "habilitado")
    @Mapping(expression = "java(setpuestoTrabajoToString(usuario.getEmpleado().getPuestosTrabajo()))", target = "sucursalesTrabajo")  
    EmpleadoIndexDto empleadotoEmpleadoIndexDto(Usuario usuario);
      
    default Set<String> setpuestoTrabajoToString(List<EspecificacionDePuesto> puestos){
        Set<String> nombreSucursalesTrabajo = new HashSet<>();
        for (EspecificacionDePuesto p : puestos) {
            nombreSucursalesTrabajo.add(p.getSucursal().getNombre());
        }
        return nombreSucursalesTrabajo;
    }
    
    default Page<EmpleadoIndexDto> toEmpleadoIndexDtoPage(Page<Usuario> usuariosPage){
        Page<EmpleadoIndexDto> empleadosDto = usuariosPage.map(this::empleadotoEmpleadoIndexDto);
        return empleadosDto;
    }
}
