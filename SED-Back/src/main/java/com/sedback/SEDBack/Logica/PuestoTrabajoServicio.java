/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sedback.SEDBack.Logica;

import com.sedback.SEDBack.HttpMensajes.HttpMensaje;
import com.sedback.SEDBack.Modelo.PuestoTrabajo;
import com.sedback.SEDBack.Persistencia.PuestoTrabajoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author ususario
 */
@Service
public class PuestoTrabajoServicio {
    @Autowired
    private PuestoTrabajoRepositorio repositorio;
    
//    public ResponseEntity<HttpMensaje> guardar(PuestoTrabajo puestoTrabajo){
//        try{
//            if(StringUtils.isBlank(puestoTrabajo.getNombrePuesto())){
//                return ResponseEntity.badRequest().body(new HttpMensaje("El campo nombre de puesto no es valido."));
//            }
//        }catch(Exception e){
//            return ResponseEntity.badRequest().body(new HttpMensaje("No fue posible guardar el puesto de trabajo. Intente nuevamente."));
//        }
//    }
    
}
