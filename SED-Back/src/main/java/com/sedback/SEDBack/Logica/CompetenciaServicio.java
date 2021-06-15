package com.sedback.SEDBack.Logica;

import com.sedback.SEDBack.Dtos.HttpMensaje;
import com.sedback.SEDBack.Modelo.Competencia;
import com.sedback.SEDBack.Persistencia.CompetenciaRepositorio;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CompetenciaServicio {
    @Autowired
    private CompetenciaRepositorio repositorio;
    
    public ResponseEntity<HttpMensaje> guardar(Competencia competencia){
       try{
          if(StringUtils.isBlank(competencia.getNombre())){
            return ResponseEntity.badRequest().body(new HttpMensaje("El campo nombre de competencia no es valido."));
          }
          repositorio.save(competencia);
          return ResponseEntity.ok().body(new HttpMensaje("La competencia se ha registrado exitosamente."));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HttpMensaje("Excepción no controlada.Desripción: "+e));
        }
    }
    
    public ResponseEntity<Page<Competencia>> obtenerCompetencias(Pageable page){
        return ResponseEntity.ok().body(repositorio.findAll(page));
    }
    
    public ResponseEntity<List<Competencia>> obtenerCompetenciasParaSelect(){
        return ResponseEntity.ok().body(repositorio.findByOrderByNombreAsc());
    }
}
