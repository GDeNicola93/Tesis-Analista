/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sedback.SEDBack.Seguridad;

import com.sedback.SEDBack.Modelo.Usuario;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author ususario
 */
public class UsuarioPrincipal implements UserDetails { //Esta clase la utilizaremos para la autenticación
    private Long id;
    private String nombreUsuario;
    private String password;
    private boolean habilitado;
    private Collection<? extends GrantedAuthority> authorities;
    
    public UsuarioPrincipal(Long id,String nombreUsuario,String password,boolean habilitado,Collection<? extends GrantedAuthority> authorities){
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.habilitado = habilitado;
        this.authorities = authorities;
    }
    
    public static UsuarioPrincipal build(Usuario usuario){
        List<GrantedAuthority> authorities = usuario.getRoles().stream().map(perfil -> new SimpleGrantedAuthority(perfil.getRolNombre().name())).collect(Collectors.toList());
        return new UsuarioPrincipal(usuario.getId(),usuario.getNombreUsuario(),usuario.getPassword(),usuario.isHabilitado(),authorities);
    }

    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return nombreUsuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return habilitado;
    }
    
}
