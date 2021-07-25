package com.sedback.SEDBack.Persistencia;

import com.sedback.SEDBack.Modelo.Competencia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetenciaRepositorio extends JpaRepository <Competencia,Integer> {
    List<Competencia> findByOrderByNombreAsc();
    
    @Query("select c from Competencia c where c.nombre = ?1 AND c.enCurso = true")
    List<Competencia> existeNombreCompetencia(String nombre);
}
