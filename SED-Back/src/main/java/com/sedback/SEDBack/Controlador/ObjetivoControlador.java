package com.sedback.SEDBack.Controlador;

import com.sedback.SEDBack.Dtos.HttpMensaje;
import com.sedback.SEDBack.Logica.ObjetivoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*") //Para poder acceder desde cualquier lado a mi backend
@RestController
@RequestMapping("/objetivo")
public class ObjetivoControlador {
    @Autowired
    private ObjetivoServicio servicio;
    
    @GetMapping("/eliminar/{id_objetivo}")
    @PreAuthorize("hasAuthority('Administrador')")
    public ResponseEntity<HttpMensaje> eliminar(@PathVariable(value="id_objetivo") Integer id_objetivo){
        return servicio.eliminar(id_objetivo);
    }
}
