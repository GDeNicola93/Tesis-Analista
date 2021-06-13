package com.sedback.SEDBack.Logica;

import com.sedback.SEDBack.Modelo.Objetivo;
import com.sedback.SEDBack.Persistencia.ObjetivoRepositorio;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ObjetivoServicio {
    @Autowired
    private ObjetivoRepositorio repositorio;
    
    public boolean guardar(Set<Objetivo> objetivos){
        try{
            for(Objetivo obj : objetivos){
                repositorio.save(obj);
            }
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
