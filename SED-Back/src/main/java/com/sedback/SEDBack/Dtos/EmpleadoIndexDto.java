package com.sedback.SEDBack.Dtos;

import java.util.Set;
import org.springframework.beans.factory.annotation.Value;


public interface EmpleadoIndexDto {
    
    @Value("#{target.getEmpleado().getId()}")
    Integer getIdEmpleado();
    
    @Value("#{target.getEmpleado().getLegajo()}")
    String getLegajo();
    
    @Value("#{target.getEmpleado().getNombre() + ' ' + target.getEmpleado().getApellido()}")
    String getNombreApellido();
    
    @Value("#{target.isHabilitado()}")
    boolean getHabilitado();
    
    @Value("#{target.getEmpleado().getNombreSucursalesDondeTrabaja()}")
    Set<String> getSucursalesTrabajo();
}
