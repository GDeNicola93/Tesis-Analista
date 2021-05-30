package com.sedback.SEDBack.Dtos;

import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmpleadoIndexDto {
    private Integer idEmpleado;
    
    private String legajo;
    
    private String nombreApellido;
    
    private boolean habilitado;
    
    private Set<String> sucursalesTrabajo = new HashSet<>();

}
