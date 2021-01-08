/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sedback.SEDBack.Controlador;

import com.sedback.SEDBack.HttpMensajes.HttpMensaje;
import com.sedback.SEDBack.HttpMensajes.JwtDTO;
import com.sedback.SEDBack.HttpMensajes.LoginUsuario;
import com.sedback.SEDBack.Logica.UsuarioServicio;
import com.sedback.SEDBack.Modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/autenticacion")
public class UsuarioControlador {
    @Autowired
    private UsuarioServicio servicio;
    
    @PostMapping("/nuevo")
    @PreAuthorize("hasAuthority('Administrador')")
     public ResponseEntity<HttpMensaje> guardar(@RequestBody Usuario usuario){
        return servicio.guardar(usuario);
    }
     
    @PostMapping("/login")
    public ResponseEntity<JwtDTO> login(@RequestBody LoginUsuario loginUsuario){
        return servicio.login(loginUsuario);
    }
    
}
