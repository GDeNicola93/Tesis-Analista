package com.sedback.SEDBack.Persistencia;

import com.sedback.SEDBack.Modelo.EspecificacionDePuesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecificacionDePuestoRepositorio extends JpaRepository <EspecificacionDePuesto,Integer> {
    
}
