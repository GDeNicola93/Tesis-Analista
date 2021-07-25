package com.sedback.SEDBack.Logica;

import com.sedback.SEDBack.Modelo.Competencia;
import com.sedback.SEDBack.Persistencia.CompetenciaRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CompetenciaServicio {
    @Autowired
    private CompetenciaRepositorio repositorio;
    
    public String guardar(Competencia competencia){
        competencia.setEnCurso(true);
        repositorio.save(competencia);
        return "La competencia se ha registrado exitosamente.";
    }
    
    public ResponseEntity<Page<Competencia>> obtenerCompetencias(Pageable page){
        return ResponseEntity.ok().body(repositorio.findAll(page));
    }
    
    public ResponseEntity<List<Competencia>> obtenerCompetenciasParaSelect(){
        return ResponseEntity.ok().body(repositorio.findByOrderByNombreAsc());
    }
    
    public boolean existeCompetencia(String nombreCompetencia){
        return repositorio.existeNombreCompetencia(nombreCompetencia).isEmpty();
    }
}
