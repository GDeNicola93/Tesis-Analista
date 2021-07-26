package com.sedback.SEDBack.Logica;

import com.sedback.SEDBack.Dtos.HttpMensaje;
import com.sedback.SEDBack.Modelo.Area;
import com.sedback.SEDBack.Persistencia.AreaRepositorio;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class AreaServicio {
    @Autowired
    private AreaRepositorio repositorio;
    
    public String guardar(Area area){
        repositorio.save(area);
        return "El área se ha registrado exitosamente.";
    }
    
    public ResponseEntity<List<Area>> obtenerAreas(){
        return ResponseEntity.ok().body(repositorio.findAll());
    }
    
    public boolean existeArea(String nombreArea){
        return repositorio.existeNombreArea(nombreArea).isEmpty();
    }
}
