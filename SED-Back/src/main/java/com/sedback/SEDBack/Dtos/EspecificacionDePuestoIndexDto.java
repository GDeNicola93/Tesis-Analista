package com.sedback.SEDBack.Dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EspecificacionDePuestoIndexDto {
    private Integer idEspecificacionDePuesto;
    
    private String nombrePuesto;
    
    private String sucursalNombre;
}
