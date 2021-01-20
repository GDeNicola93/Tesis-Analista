/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sedback.SEDBack.Logica;

import com.sedback.SEDBack.Modelo.Rol;
import com.sedback.SEDBack.Persistencia.RolRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author ususario
 */
@Service
public class RolServicio {
    @Autowired
    private RolRepositorio repositorio;
    
     public ResponseEntity<List<Rol>> obtenerRoles(){
        return ResponseEntity.ok().body(repositorio.findAll());
    }
}
