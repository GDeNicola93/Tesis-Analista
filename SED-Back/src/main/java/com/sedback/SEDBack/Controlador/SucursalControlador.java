/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sedback.SEDBack.Controlador;

import com.sedback.SEDBack.HttpMensajes.HttpMensaje;
import com.sedback.SEDBack.Logica.SucursalServicio;
import com.sedback.SEDBack.Modelo.Sucursal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ususario
 */
@CrossOrigin(origins = "*") //Para poder acceder desde cualquier lado a mi backend
@RestController
@RequestMapping("/sucursal")
public class SucursalControlador {
    @Autowired
    private SucursalServicio servicio;
    
    @PostMapping
    public ResponseEntity<HttpMensaje> guardar(@RequestBody Sucursal sucursal){
        return servicio.guardar(sucursal);
    }
    
    @GetMapping
    public ResponseEntity<List<Sucursal>> obtenerAreas(){
        return servicio.obtenerSucursales();
    }
}
