package com.sedback.SEDBack.Controlador;

import com.sedback.SEDBack.Dtos.HttpMensaje;
import com.sedback.SEDBack.Logica.SucursalServicio;
import com.sedback.SEDBack.Modelo.Sucursal;
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
@RequestMapping("/sucursal")
public class SucursalControlador {
    @Autowired
    private SucursalServicio servicio;
    
    @PostMapping
    @PreAuthorize("hasAuthority('Administrador')")
    public ResponseEntity<HttpMensaje> guardar(@RequestBody Sucursal sucursal){
        return servicio.guardar(sucursal);
    }
    
    @GetMapping
    @PreAuthorize("hasAuthority('Administrador')")
    public ResponseEntity<Page<Sucursal>> getSucursales(Pageable page){
        return servicio.getSucursales(page);
    }
}
