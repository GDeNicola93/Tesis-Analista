package com.sedback.SEDBack.Logica;

import com.sedback.SEDBack.Modelo.DetallePlantilla;
import com.sedback.SEDBack.Persistencia.CompetenciaRepositorio;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompetenciaServicio {
    @Autowired
    private CompetenciaRepositorio repositorio;
    
    
    public void guardarCompetencias(Set<DetallePlantilla> dp){
        for(DetallePlantilla x : dp){
            repositorio.save(x.getCompetencia());
        }
    }
}
