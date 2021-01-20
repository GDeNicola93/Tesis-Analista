/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sedback.SEDBack.Logica;

import com.sedback.SEDBack.HttpMensajes.HttpMensaje;
import com.sedback.SEDBack.Modelo.Empleado;
import com.sedback.SEDBack.Persistencia.EmpleadoRepositorio;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author ususario
 */
@Service
public class EmpleadoServicio {
    @Autowired
    private EmpleadoRepositorio repositorio;
    
    public ResponseEntity<HttpMensaje> guardar(Empleado empleado){
        try{
            //Primero verifico que sea correcta la información del empleado
            if(StringUtils.isBlank(empleado.getLegajo())){
                return ResponseEntity.badRequest().body(new HttpMensaje("El campo legajo no es valido."));
            }
            if(StringUtils.isBlank(empleado.getNombre())){
                return ResponseEntity.badRequest().body(new HttpMensaje("El campo nombre no es valido."));
            }
            if(StringUtils.isBlank(empleado.getApellido())){
                return ResponseEntity.badRequest().body(new HttpMensaje("El campo apellido no es valido."));
            }
            if(empleado.getFechaDeNacimiento() == null){
                return ResponseEntity.badRequest().body(new HttpMensaje("El campo fecha de nacimiento no es valido."));
            }
            return ResponseEntity.ok().body(new HttpMensaje("Paso todas las pruebas"));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(new HttpMensaje("Excepción no controlada.Desripción: "+e));
        }
    }
}
