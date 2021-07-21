package com.sedback.SEDBack.Logica;

import com.sedback.SEDBack.Dtos.HttpMensaje;
import com.sedback.SEDBack.Modelo.Objetivo;
import com.sedback.SEDBack.Persistencia.ObjetivoRepositorio;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class ObjetivoServicio {
    @Autowired
    private ObjetivoRepositorio repositorio;
    
    public boolean guardar(List<Objetivo> objetivos){
        try{
            for(Objetivo obj : objetivos){
                repositorio.save(obj);
            }
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    public ResponseEntity<HttpMensaje> eliminar(Integer id_objetivo){
        Objetivo obj = repositorio.findById(id_objetivo).get();
        obj.sacarDeCurso();
        repositorio.save(obj);
        return ResponseEntity.ok().body(new HttpMensaje("Objetivo eliminado exitosamente de la especificación de puesto."));
    }
}
