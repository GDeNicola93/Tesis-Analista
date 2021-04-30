package com.sedback.SEDBack.HttpMensajes;

import com.sedback.SEDBack.Modelo.PuestoTrabajo;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;

@Data
public class EmpleadoIndex {
    private String legajo;
    
    private String nombre;
   
    private String apellido;
    
    private Set<PuestoTrabajo> puestosTrabajo = new HashSet<>();
}
