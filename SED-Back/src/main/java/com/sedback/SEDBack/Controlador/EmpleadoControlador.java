/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sedback.SEDBack.Controlador;

import com.sedback.SEDBack.HttpMensajes.HttpMensaje;
import com.sedback.SEDBack.Logica.EmpleadoServicio;
import com.sedback.SEDBack.Modelo.Empleado;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/empleados")
public class EmpleadoControlador {
    
    @Autowired
    private EmpleadoServicio servicio;
    
    @GetMapping
    @PreAuthorize("hasAuthority('Administrador')")
    public ResponseEntity<List<Empleado>> obtenerEmpleados(){
        return servicio.obtenerEmpleados();
    }
    
    @GetMapping(params = {"nombreApellido"})
    @PreAuthorize("hasAuthority('Administrador')")
    public ResponseEntity<List<Empleado>> buscarPorNombreYApellido(String nombreApellido){
        return servicio.buscarPorNombreYApellido(nombreApellido);
    }
    
    @GetMapping(params = {"legajo"})
    @PreAuthorize("hasAuthority('Administrador')")
    public ResponseEntity<List<Empleado>> buscarPorLegajo(String legajo){
        return servicio.buscarPorLegajo(legajo);
    }
    
    
}
