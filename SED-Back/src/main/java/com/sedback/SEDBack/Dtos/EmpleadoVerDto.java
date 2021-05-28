package com.sedback.SEDBack.Dtos;

import com.sedback.SEDBack.Modelo.Rol;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;

@Data
public class EmpleadoVerDto {
    private String legajo;
    
    private String nombreApellido;
    
    private String dni;
    
    private LocalDate fechaDeNacimiento;
    
    private String nombreUsuario;
    
    private String nombreFotoPerfil;
    
    private String email;
    
    private Set<Rol> roles = new HashSet<>();
    
    private boolean habilitado;
    
    private Set<String> puestos = new HashSet<>();
}
