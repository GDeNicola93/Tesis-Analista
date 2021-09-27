package com.sedback.SEDBack.Logica;

import com.sedback.SEDBack.Modelo.Resultado;
import com.sedback.SEDBack.Persistencia.ResultadoRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultadoServicio {
    @Autowired
    private ResultadoRepositorio repositorio;
    
    public void guardarResultados(List<Resultado> resultados){
        for(Resultado r : resultados){
            repositorio.save(r);
        }
    }
}
