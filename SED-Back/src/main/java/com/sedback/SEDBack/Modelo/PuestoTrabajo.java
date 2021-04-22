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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author ususario
 */
@Entity
@Data
@NoArgsConstructor
public class PuestoTrabajo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotNull
    @Column(unique = false)
    private String nombrePuesto;
    
    @NotNull
    @ManyToOne
    private Sucursal sucursal;
    
    @NotNull
    @ManyToOne
    private Area area;
    
    @OneToMany
    private Set<Objetivo> objetivosPuesto = new HashSet<>();
}
