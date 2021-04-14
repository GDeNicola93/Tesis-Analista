package com.sedback.SEDBack.Logica;

import com.sedback.SEDBack.Modelo.DetallePlantilla;
import com.sedback.SEDBack.Persistencia.DetallePlantillaRepositorio;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetallePlantillaServicio {
    @Autowired
    private DetallePlantillaRepositorio repositorio;
    
    public void guardarDetallePlantilla(Set<DetallePlantilla> dp){
        for(DetallePlantilla x : dp){
            repositorio.save(x);
        }
    }
}
