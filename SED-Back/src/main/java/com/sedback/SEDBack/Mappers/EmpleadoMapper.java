package com.sedback.SEDBack.Mappers;

import com.sedback.SEDBack.Dtos.EmpleadoIndexDto;
import com.sedback.SEDBack.Dtos.EmpleadoVerDto;
import com.sedback.SEDBack.Modelo.PuestoTrabajo;
import com.sedback.SEDBack.Modelo.Usuario;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class EmpleadoMapper {
    
    public EmpleadoIndexDto toDtoIndex(Usuario usuario){
        EmpleadoIndexDto dto = new EmpleadoIndexDto();
        dto.setId(usuario.getEmpleado().getId());
        dto.setLegajo(usuario.getEmpleado().getLegajo());
        dto.setNombreApellido(usuario.getEmpleado().getNombre()+" "+usuario.getEmpleado().getApellido());
        dto.setHabilitado(usuario.isHabilitado());
        Set<PuestoTrabajo> puestos = usuario.getEmpleado().getPuestosTrabajo();
        Set<String> sucursales = new HashSet<>();
        puestos.forEach((puesto) -> {sucursales.add(puesto.getSucursal().getNombre());});
        dto.setLugaresTrabajo(sucursales);
        return dto;
    }
    
    public EmpleadoVerDto toDtoVer(Usuario usuario){
        EmpleadoVerDto dto = new EmpleadoVerDto();
        dto.setLegajo(usuario.getEmpleado().getLegajo());
        dto.setNombreApellido(usuario.getEmpleado().getNombre()+" "+usuario.getEmpleado().getApellido());
        dto.setDni(usuario.getEmpleado().getDni());
        dto.setFechaDeNacimiento(usuario.getEmpleado().getFechaDeNacimiento());
        dto.setNombreUsuario(usuario.getNombreUsuario());
        dto.setNombreFotoPerfil(usuario.getNombreFotoPerfil());
        dto.setEmail(usuario.getEmpleado().getEmail());
        dto.setRoles(usuario.getRoles());
        dto.setHabilitado(usuario.isHabilitado());
        Set<PuestoTrabajo> puestosT = usuario.getEmpleado().getPuestosTrabajo();
        Set<String> puestos = new HashSet<>();
        puestosT.forEach((puesto) -> {puestos.add(puesto.getNombrePuesto() +" ("+puesto.getSucursal().getNombre()+")");});
        dto.setPuestos(puestos);
        return dto;
    }
}
