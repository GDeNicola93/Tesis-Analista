package com.sedback.SEDBack.Persistencia;

import com.sedback.SEDBack.Modelo.ComportamientoPlantilla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComportamientoPlantillaRepositorio extends JpaRepository<ComportamientoPlantilla,Integer>{
    
    
}
