/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sedback.SEDBack.Controlador;

import com.sedback.SEDBack.Dtos.EmpleadoIndexDto;
import com.sedback.SEDBack.Dtos.EmpleadoVerDto;
import com.sedback.SEDBack.Dtos.HttpMensaje;
import com.sedback.SEDBack.Logica.EmpleadoServicio;
import com.sedback.SEDBack.Modelo.Empleado;
import com.sedback.SEDBack.Modelo.Usuario;
import java.util.List;
import java.util.Optional;
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
      
//    @GetMapping(params = {"id"})
//    @PreAuthorize("hasAuthority('Administrador')")
//    public ResponseEntity<EmpleadoVerDto> getEmpleadoById(Integer id){
//        return servicio.getEmpleadoById(id);
//    }
    
    @GetMapping(params = {"filtro"})
    @PreAuthorize("hasAuthority('Administrador')")
    public ResponseEntity<Page<EmpleadoIndexDto>> getEmpleados(String filtro,Pageable page){
        return servicio.getEmpleados(filtro,page);
    }

}
