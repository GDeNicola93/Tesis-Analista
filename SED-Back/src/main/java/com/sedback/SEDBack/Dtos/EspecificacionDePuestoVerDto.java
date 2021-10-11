package com.sedback.SEDBack.Dtos;

import com.sedback.SEDBack.Modelo.Objetivo;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;


public interface EspecificacionDePuestoVerDto {
    Integer getId();
    
    @Value("#{target.getPuesto().getNombrePuesto()}")
    String getNombrePuesto();
    
    String getDescripcion();
    
    @Value("#{target.getSucursal().getNombre()}")
    String getSucursalNombre();
    
    @Value("#{target.getPuesto().getArea().getNombre()}")
    String getAreaNombre();
    
    List<Objetivo> getObjetivosActivos();
}
