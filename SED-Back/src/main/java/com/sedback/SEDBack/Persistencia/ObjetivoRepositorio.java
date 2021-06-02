package com.sedback.SEDBack.Persistencia;

import com.sedback.SEDBack.Modelo.Objetivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjetivoRepositorio extends JpaRepository<Objetivo,Integer> {
    
}
