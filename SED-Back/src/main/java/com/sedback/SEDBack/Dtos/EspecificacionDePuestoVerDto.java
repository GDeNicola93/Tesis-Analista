package com.sedback.SEDBack.Dtos;

import com.sedback.SEDBack.Modelo.Objetivo;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EspecificacionDePuestoVerDto {
    private Integer idEspecificacionDePuesto;
    
    private String nombrePuesto;
    
    private String descripcion;
    
    private String sucursalNombre;
    
    private String areaNombre;
    
    private Set<Objetivo> objetivos = new HashSet<>();
}
