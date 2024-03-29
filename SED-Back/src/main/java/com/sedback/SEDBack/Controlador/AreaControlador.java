package com.sedback.SEDBack.Controlador;

import com.sedback.SEDBack.Dtos.HttpMensaje;
import com.sedback.SEDBack.Excepciones.InvalidDataException;
import com.sedback.SEDBack.Logica.AreaServicio;
import com.sedback.SEDBack.Modelo.Area;
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
@RequestMapping("/area")
public class AreaControlador {
    @Autowired
    private AreaServicio servicio;
    
    @PostMapping
    @PreAuthorize("hasAuthority('Administrador')")
    public ResponseEntity<HttpMensaje> guardar(@Valid @RequestBody Area area,BindingResult result){
        if (result.hasErrors()) {
            throw new InvalidDataException(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(new HttpMensaje(servicio.guardar(area)));
    }
    
    @GetMapping
    @PreAuthorize("hasAuthority('Administrador')")
    public ResponseEntity<List<Area>> obtenerAreas(){
        return servicio.obtenerAreas();
    }
}
