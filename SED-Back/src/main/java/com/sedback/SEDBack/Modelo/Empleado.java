/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sedback.SEDBack.Modelo;

import com.sun.istack.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author ususario
 */
@Entity
@Data
@NoArgsConstructor
public class Empleado implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotNull
    @Column(unique = true)
    private String legajo;
    
    @NotNull
    private String nombre;
    
    @NotNull
    private String apellido;
    
    @NotNull
    private String dni;
    
    @NotNull
    private String email;
    
    @NotNull
    private LocalDate fechaDeNacimiento;
    
    @NotNull
    @ManyToOne
    private Estado estado;
    
    @NotNull
    @ManyToMany
    private Set<PuestoTrabajo> puestosTrabajo = new HashSet<>();
}
