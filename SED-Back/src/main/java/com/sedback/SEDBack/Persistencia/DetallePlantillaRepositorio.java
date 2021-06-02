package com.sedback.SEDBack.Persistencia;

import com.sedback.SEDBack.Modelo.DetallePlantilla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetallePlantillaRepositorio extends JpaRepository<DetallePlantilla,Integer>{
    
}
