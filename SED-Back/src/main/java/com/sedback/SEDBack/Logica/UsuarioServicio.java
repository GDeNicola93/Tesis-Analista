/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sedback.SEDBack.Logica;

import com.sedback.SEDBack.HttpMensajes.HttpMensaje;
import com.sedback.SEDBack.HttpMensajes.JwtDTO;
import com.sedback.SEDBack.HttpMensajes.LoginUsuario;
import com.sedback.SEDBack.Modelo.Usuario;
import com.sedback.SEDBack.Persistencia.UsuarioRepositorio;
import com.sedback.SEDBack.Seguridad.JWT.JwtProvider;
import com.sedback.SEDBack.Seguridad.JWT.JwtTokenFilter;
import java.util.Optional;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ususario
 */
@Service
@Transactional
public class UsuarioServicio {
    @Autowired
    UsuarioRepositorio repositorio;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    private EmpleadoServicio empleadoServicio;
    
    @Autowired
    JwtProvider jwtProvider;
    
    public Optional<Usuario> getByNombreUsuario(String nu){
        return repositorio.findByNombreUsuario(nu);
    }
    
    public ResponseEntity<HttpMensaje> guardar(Usuario usuario){
        try{
            ResponseEntity<HttpMensaje> respuesta = empleadoServicio.verificarCampos(usuario.getEmpleado());
            if(respuesta.getStatusCode().isError()){
                return respuesta;
            }
            if(StringUtils.isBlank(usuario.getNombreUsuario())){
                return ResponseEntity.badRequest().body(new HttpMensaje("El campo nombre de usuario no es valido."));
            }else{
                Optional<Usuario> existeUsuario = repositorio.findByNombreUsuario(usuario.getNombreUsuario());
                if (!existeUsuario.isEmpty()){
                    return ResponseEntity.badRequest().body(new HttpMensaje("El nombre de usuario ingresado ya existe en la base de datos. Por favor escoja otro."));
                }
            }
            if(StringUtils.isBlank(usuario.getPassword())){
                return ResponseEntity.badRequest().body(new HttpMensaje("El campo contraseña no es valido."));
            }
            if(ObjectUtils.isEmpty(usuario.getRoles())){
                return ResponseEntity.badRequest().body(new HttpMensaje("Debe seleccionar un perfil de usuario valida."));
            }
            String passSinEncriptar = usuario.getPassword();
            usuario.setPassword(passwordEncoder.encode(passSinEncriptar));
            empleadoServicio.guardar(usuario.getEmpleado());
            repositorio.save(usuario);
            return ResponseEntity.ok().body(new HttpMensaje("El usuario se ha creado exitosamente."));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(new HttpMensaje("Excepción no controlada.Desripción: "+e));
        }
    }
    
    public ResponseEntity<JwtDTO> login(LoginUsuario loginUsuario){
        try{
            if(StringUtils.isBlank(loginUsuario.getNombreUsuario())){
                return new ResponseEntity(new HttpMensaje("El nombre de usuario no es valido."), HttpStatus.BAD_REQUEST);
            }
            if(StringUtils.isBlank(loginUsuario.getPassword())){
                return new ResponseEntity(new HttpMensaje("El password no es valido."), HttpStatus.BAD_REQUEST);
            }
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtProvider.generateToken(authentication);
            JwtDTO jwtDTO = new JwtDTO(jwt);
            return new ResponseEntity<JwtDTO>(jwtDTO, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(new HttpMensaje("No fue posible ingresar ahora. Intente nuevamente mas tarde."), HttpStatus.BAD_REQUEST);
        }
    }
    
    public ResponseEntity<Usuario> getDatosUsuarioLogeadoToken(String token){
        return ResponseEntity.ok().body(repositorio.findById(jwtProvider.getIdUserFromToken(token)).get());
    }
    
    
}
