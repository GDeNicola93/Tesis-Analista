/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sedback.SEDBack.Modelo;

import com.sun.istack.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author ususario
 */
@Entity
@Data
@NoArgsConstructor
public class Usuario implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Column(unique = true)
    private String nombreUsuario;
    
    @NotNull
    private String password;
    
    @NotNull
    private boolean habilitado;
    
    @ManyToMany
    private Set<Rol> roles = new HashSet<>();
    
    @OneToOne
    private Empleado empleado;
    
    private String nombreFotoPerfil;
}
