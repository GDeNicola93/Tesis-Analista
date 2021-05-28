/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sedback.SEDBack.Logica;

import com.sedback.SEDBack.Dtos.HttpMensaje;
import com.sedback.SEDBack.Modelo.Area;
import com.sedback.SEDBack.Persistencia.AreaRepositorio;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author ususario
 */
@Service
public class AreaServicio {
    @Autowired
    private AreaRepositorio repositorio;
    
    public ResponseEntity<HttpMensaje> guardar(Area area){
        try{
            if(StringUtils.isBlank(area.getNombre())){
                return ResponseEntity.badRequest().body(new HttpMensaje("El campo nombre de área no es valido."));
            }
            repositorio.save(area);
            return ResponseEntity.ok().body(new HttpMensaje("El área se ha registrado exitosamente."));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(new HttpMensaje("No fue posible registrar el área. Intente nuevamente."));
        }
    }
    
    public ResponseEntity<List<Area>> obtenerAreas(){
        return ResponseEntity.ok().body(repositorio.findAll());
    }
    
}
