package com.sedback.SEDBack.Persistencia;

import com.sedback.SEDBack.Modelo.Resultado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultadoRepositorio extends JpaRepository<Resultado,Long>{
    
}
