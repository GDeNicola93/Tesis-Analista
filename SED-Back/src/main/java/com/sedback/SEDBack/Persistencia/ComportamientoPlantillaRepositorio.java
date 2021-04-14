package com.sedback.SEDBack.Persistencia;

import com.sedback.SEDBack.Modelo.ComportamientoPlantilla;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ComportamientoPlantillaRepositorio extends JpaRepository<ComportamientoPlantilla,Integer>{
    
    
}
