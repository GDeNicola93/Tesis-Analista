package com.sedback.SEDBack.Controlador;

import com.sedback.SEDBack.Dtos.HttpMensaje;
import com.sedback.SEDBack.Excepciones.InvalidDataException;
import com.sedback.SEDBack.Logica.PuestoTrabajoServicio;
import com.sedback.SEDBack.Modelo.PuestoTrabajo;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*") //Para poder acceder desde cualquier lado a mi backend
@RestController
@RequestMapping("/puesto-trabajo")
public class PuestoTrabajoControlador {
    @Autowired
    private PuestoTrabajoServicio servicio;
    
    @PostMapping
    @PreAuthorize("hasAuthority('Administrador')")
    public ResponseEntity<HttpMensaje> guardar(@Valid @RequestBody PuestoTrabajo puestoTrabajo,BindingResult result){
        if (result.hasErrors()) {
            throw new InvalidDataException(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(new HttpMensaje(servicio.guardar(puestoTrabajo)));
    }
    
    @GetMapping
    @PreAuthorize("hasAuthority('Administrador')")
    public ResponseEntity<List<PuestoTrabajo>> getPuestosTrabajoSelect(){
        return servicio.getPuestosTrabajoSelect();
    }
}
