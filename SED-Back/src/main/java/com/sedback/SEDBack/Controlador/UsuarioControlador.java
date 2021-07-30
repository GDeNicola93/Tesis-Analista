package com.sedback.SEDBack.Controlador;

import com.sedback.SEDBack.Dtos.CambioPassword;
import com.sedback.SEDBack.Dtos.HttpMensaje;
import com.sedback.SEDBack.Dtos.JwtDTO;
import com.sedback.SEDBack.Dtos.LoginUsuario;
import com.sedback.SEDBack.Logica.UsuarioServicio;
import com.sedback.SEDBack.Modelo.Usuario;
import com.sedback.SEDBack.Seguridad.UsuarioPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*") //Para poder acceder desde cualquier lado a mi backend
@RestController
@RequestMapping("/usuario")
public class UsuarioControlador {
    @Autowired
    private UsuarioServicio servicio;
     
    @GetMapping("/obtener_datos")
    public ResponseEntity<Usuario> obtenerDatos(){
       UsuarioPrincipal userLogeado = (UsuarioPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       return servicio.getDatosUsuarioLogeado(userLogeado.getId());  
    }
     
    @PostMapping("/login")
    public ResponseEntity<JwtDTO> login(@RequestBody LoginUsuario loginUsuario){
        return servicio.login(loginUsuario);
    }
    
    @PostMapping("/update_password")
    public ResponseEntity<HttpMensaje> actualizarPassword(@RequestBody CambioPassword cambioPassword){
        UsuarioPrincipal userLogeado = (UsuarioPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return servicio.actualizarPassword(cambioPassword,servicio.getDatosUsuarioLogeado(userLogeado.getId()).getBody());
    }
}
