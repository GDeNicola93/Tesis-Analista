package com.sedback.SEDBack.Dtos;

import org.springframework.beans.factory.annotation.Value;


public interface EspecificacionDePuestoIndexDto {
    Integer getId();
    
    @Value("#{target.getPuesto().getNombrePuesto()}")
    String getNombrePuesto();
    
    @Value("#{target.getSucursal().getNombre()}")
    String getSucursalNombre();
}
