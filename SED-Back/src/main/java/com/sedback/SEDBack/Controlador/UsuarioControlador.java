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
import com.sedback.SEDBack.Seguridad.JWT.JwtProvider;
import io.jsonwebtoken.Jwts;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ususario
 */
@CrossOrigin(origins = "*") //Para poder acceder desde cualquier lado a mi backend
@RestController
@RequestMapping("/usuario")
public class UsuarioControlador {
    @Autowired
    private UsuarioServicio servicio;
    
    @PostMapping("/nuevo")
    @PreAuthorize("hasAuthority('Administrador')")
    public ResponseEntity<HttpMensaje> guardar(@RequestBody Usuario usuario){
        return servicio.guardar(usuario);
    }
     
    @GetMapping("/obtener_datos")
    public ResponseEntity<Usuario> obtenerDatos(@RequestHeader("authorization") String language){
        String token = language.replace("Bearer ", "");
        return servicio.getDatosUsuarioLogeadoToken(token);  
    }
     
    @PostMapping("/login")
    public ResponseEntity<JwtDTO> login(@RequestBody LoginUsuario loginUsuario){
        return servicio.login(loginUsuario);
    }
}
