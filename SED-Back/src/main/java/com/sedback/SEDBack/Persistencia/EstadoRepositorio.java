package com.sedback.SEDBack.Persistencia;

import com.sedback.SEDBack.Modelo.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepositorio extends JpaRepository<Estado,Integer> {
    
}
