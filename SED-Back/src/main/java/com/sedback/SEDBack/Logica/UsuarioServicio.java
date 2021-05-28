/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sedback.SEDBack.Logica;

import com.sedback.SEDBack.Dtos.CambioPassword;
import com.sedback.SEDBack.Dtos.HttpMensaje;
import com.sedback.SEDBack.Dtos.JwtDTO;
import com.sedback.SEDBack.Dtos.LoginUsuario;
import com.sedback.SEDBack.Modelo.Usuario;
import com.sedback.SEDBack.Persistencia.UsuarioRepositorio;
import com.sedback.SEDBack.Seguridad.JWT.JwtProvider;
import com.sedback.SEDBack.Seguridad.JWT.JwtTokenFilter;
import java.io.File;
import java.nio.file.Files;
import java.util.Optional;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    
    @Value("${UPLOAD_DIR}")
    String UPLOAD_DIR;
    
    public Optional<Usuario> getByNombreUsuario(String nu){
        return repositorio.findByNombreUsuario(nu);
    }
    
    public ResponseEntity<HttpMensaje> setImagenPerfil(Usuario user,String file_name){
        try{
            if(user.getNombreFotoPerfil() != null){
                File imagenVieja = new File(this.UPLOAD_DIR+user.getNombreFotoPerfil()+".png");
                imagenVieja.delete();
            }
            user.setNombreFotoPerfil(file_name);
            repositorio.save(user);
            return ResponseEntity.ok().body(new HttpMensaje("Imagen de perfil seteada correctamente!"));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(new HttpMensaje("Excepción no controlada.Desripción: "+e));
        }
    }
    
    public ResponseEntity<HttpMensaje> actualizarPassword(CambioPassword cambioPassword,Usuario user){
        try{
            if(StringUtils.isBlank(cambioPassword.getPasswordActual())){
                return ResponseEntity.badRequest().body(new HttpMensaje("El campo contraseña actual no es valido."));
            }
            if(StringUtils.equals(cambioPassword.getPasswordNew1(), cambioPassword.getPasswordNew2())){
                if(StringUtils.isBlank(cambioPassword.getPasswordNew1())){
                    return ResponseEntity.badRequest().body(new HttpMensaje("El campo nueva contraseña no es valido."));
                }
                if(StringUtils.isBlank(cambioPassword.getPasswordNew2())){
                    return ResponseEntity.badRequest().body(new HttpMensaje("El campo repetición de nueva contraseña no es valido."));
                }
                if(passwordEncoder.matches(cambioPassword.getPasswordActual(),user.getPassword())){
                    if(StringUtils.equals(cambioPassword.getPasswordNew1(),cambioPassword.getPasswordActual())){
                        return ResponseEntity.badRequest().body(new HttpMensaje("La contraseña actual y la nueva no deben ser iguales."));
                    }
                    user.setPassword(passwordEncoder.encode(cambioPassword.getPasswordNew1()));
                    repositorio.save(user);
                    return ResponseEntity.ok().body(new HttpMensaje("La contraseña se ha actualizado correctamente."));
                }else{
                    return ResponseEntity.badRequest().body(new HttpMensaje("La contraseña actual no es correcta."));
                }
            }else{
                return ResponseEntity.badRequest().body(new HttpMensaje("La nueva contraseña y la repetición de la nueva contraseña no coinciden."));
            }
        }catch(Exception e){
            return ResponseEntity.badRequest().body(new HttpMensaje("Excepción no controlada.Desripción: "+e));
        }
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
