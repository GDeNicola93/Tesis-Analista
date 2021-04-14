package com.sedback.SEDBack.Persistencia;

import com.sedback.SEDBack.Modelo.Competencia;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CompetenciaRepositorio extends JpaRepository <Competencia,Integer> {
    
}
