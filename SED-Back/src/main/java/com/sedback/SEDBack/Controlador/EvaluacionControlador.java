package com.sedback.SEDBack.Controlador;

import com.fasterxml.jackson.annotation.JsonView;
import com.sedback.SEDBack.Dtos.EvaluacionEvaluadorIndexDto;
import com.sedback.SEDBack.Dtos.EvaluacionIndexDto;
import com.sedback.SEDBack.Dtos.EvaluacionVerDto;
import com.sedback.SEDBack.Dtos.HttpMensaje;
import com.sedback.SEDBack.Dtos.NuevaEvaluacionDto;
import com.sedback.SEDBack.Logica.EvaluacionServicio;
import com.sedback.SEDBack.Modelo.Evaluacion;
import com.sedback.SEDBack.Views.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
    
    @GetMapping()
    @PreAuthorize("hasAuthority('Administrador')")
    public ResponseEntity<Page<EvaluacionIndexDto>> getEvaluaciones(Pageable page){
        return servicio.getEvaluaciones(page);
    }
    
    @GetMapping(params = {"id"})
    @PreAuthorize("hasAuthority('Administrador')" + "|| hasAuthority('Evaluador')")
    public ResponseEntity<EvaluacionVerDto> getEvaluacionById(Long id,@RequestHeader("authorization") String bearer){
        String token = bearer.replace("Bearer ", "");
        return servicio.getEvaluacionById(id,token);
    }
    
    @GetMapping("/cancelar/{id_evaluacion}")
    @PreAuthorize("hasAuthority('Administrador')")
    public ResponseEntity<HttpMensaje> cancelarEvaluacion(@PathVariable(value="id_evaluacion") Long id_evaluacion){
        return servicio.cancelarEvaluacion(id_evaluacion);
    }
    
    //Endpoints Evaluador
    
    @GetMapping("/evaluador/logeado")
    @PreAuthorize("hasAuthority('Evaluador')")
    public ResponseEntity<Page<EvaluacionEvaluadorIndexDto>> getEvaluacionesEvaluadorLogeado(@RequestHeader("authorization") String bearer,Pageable page){
        String token = bearer.replace("Bearer ", "");
        return servicio.getEvaluacionesEvaluadorLogeado(token, page);
    }
}
