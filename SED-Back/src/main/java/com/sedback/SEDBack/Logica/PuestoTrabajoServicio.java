package com.sedback.SEDBack.Logica;

import com.sedback.SEDBack.Modelo.PuestoTrabajo;
import com.sedback.SEDBack.Persistencia.PuestoTrabajoRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PuestoTrabajoServicio {
    @Autowired
    private PuestoTrabajoRepositorio repositorio;
    
    public String guardar(PuestoTrabajo puestoTrabajo){
        repositorio.save(puestoTrabajo);
        return "El puesto de trabajo se ha registrado exitosamente.";
    }
    
    public ResponseEntity<List<PuestoTrabajo>> getPuestosTrabajoSelect(){
        return ResponseEntity.ok().body(repositorio.findAll());
    }
    
}
