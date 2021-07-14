package com.sedback.SEDBack.Controlador;

import com.sedback.SEDBack.Dtos.HttpMensaje;
import com.sedback.SEDBack.Dtos.NuevaEvaluacionDto;
import com.sedback.SEDBack.Logica.EvaluacionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*") //Para poder acceder desde cualquier lado a mi backend
@RestController
@RequestMapping("/evaluacion")
public class EvaluacionControlador {
    @Autowired
    private EvaluacionServicio servicio;
    
    @PostMapping()
    @PreAuthorize("hasAuthority('Administrador')")
    public ResponseEntity<HttpMensaje> guardar(@RequestBody NuevaEvaluacionDto evaluacion){
        return servicio.guardar(evaluacion);
    }
}
