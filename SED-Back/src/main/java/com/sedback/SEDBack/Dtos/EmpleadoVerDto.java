package com.sedback.SEDBack.Dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sedback.SEDBack.Modelo.Rol;
import java.time.LocalDate;
import java.util.Set;
import org.springframework.beans.factory.annotation.Value;


public interface EmpleadoVerDto {
    @Value("#{target.getEmpleado().getLegajo()}")
    String getLegajo();
    
    @Value("#{target.getEmpleado().getNombre() + ' ' + target.getEmpleado().getApellido()}")
    String getNombreApellido();
    
    @Value("#{target.getEmpleado().getDni()}")
    String getDni();
    
    @Value("#{target.getEmpleado().getFechaDeNacimiento()}")
    @JsonFormat(pattern="dd-MM-yyyy")        
    LocalDate getFechaDeNacimiento();
    
    String getNombreUsuario();
    
    String getNombreFotoPerfil();
    
    @Value("#{target.getEmpleado().getEmail()}")
    String getEmail();
    
    Set<Rol> getRoles();
    
    @Value("#{target.isHabilitado()}")
    boolean getHabilitado();
    
    @Value("#{target.getEmpleado().getNombrePuestosTrabajoConNombreSucursal()}")
    Set<String> getPuestos();
}
