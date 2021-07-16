package com.sedback.SEDBack.Logica;

import com.sedback.SEDBack.Modelo.ComportamientoPlantilla;
import com.sedback.SEDBack.Modelo.DetallePlantilla;
import com.sedback.SEDBack.Persistencia.ComportamientoPlantillaRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComportamientoPlantillaServicio {
    @Autowired
    private ComportamientoPlantillaRepositorio repositorio;
    
    public void guardarComportamientoPlantilla(List<DetallePlantilla> dp){
        for(DetallePlantilla x : dp){
            for(ComportamientoPlantilla y : x.getComportamiento()){
                repositorio.save(y);
            }    
        }
    }
}
