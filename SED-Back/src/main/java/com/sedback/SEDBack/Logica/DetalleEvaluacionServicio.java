package com.sedback.SEDBack.Logica;

import com.sedback.SEDBack.Modelo.DetalleEvaluacion;
import com.sedback.SEDBack.Persistencia.DetalleEvaluacionRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleEvaluacionServicio {
    @Autowired
    private DetalleEvaluacionRepositorio repositorio;
    
    public void guardar(List<DetalleEvaluacion> detalles){
        for(DetalleEvaluacion de : detalles){
            repositorio.save(de);
        }
    }
}
