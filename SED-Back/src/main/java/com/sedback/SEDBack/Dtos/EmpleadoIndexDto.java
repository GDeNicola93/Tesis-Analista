package com.sedback.SEDBack.Dtos;

import com.sedback.SEDBack.Modelo.PuestoTrabajo;
import com.sedback.SEDBack.Modelo.Sucursal;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class EmpleadoIndexDto implements Serializable {
    private Integer id;
    
    private String legajo;
    
    private String nombreApellido;
    
    private boolean habilitado;
    
    private Set<String> lugaresTrabajo = new HashSet<>();

}
