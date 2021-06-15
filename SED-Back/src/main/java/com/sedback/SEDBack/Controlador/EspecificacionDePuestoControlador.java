package com.sedback.SEDBack.Controlador;

import com.sedback.SEDBack.Dtos.EspecificacionDePuestoIndexDto;
import com.sedback.SEDBack.Dtos.HttpMensaje;
import com.sedback.SEDBack.Logica.EspecificacionDePuestoServicio;
import com.sedback.SEDBack.Modelo.EspecificacionDePuesto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*") //Para poder acceder desde cualquier lado a mi backend
@RestController
@RequestMapping("/especificacion-puesto")
public class EspecificacionDePuestoControlador {
    @Autowired
    private EspecificacionDePuestoServicio servicio;
    
    @PostMapping
    @PreAuthorize("hasAuthority('Administrador')")
    public ResponseEntity<HttpMensaje> guardar(@RequestBody EspecificacionDePuesto edp){
        return servicio.guardar(edp);
    }
    
    @GetMapping
    @PreAuthorize("hasAuthority('Administrador')")
    public ResponseEntity<Page<EspecificacionDePuestoIndexDto>> getEspecificacionesDePuestos(Pageable page){
        return servicio.obtenerEspecificacionesDePuestosIndex(page);
    }
}
