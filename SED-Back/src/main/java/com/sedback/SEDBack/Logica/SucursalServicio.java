/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sedback.SEDBack.Logica;

import com.sedback.SEDBack.Dtos.HttpMensaje;
import com.sedback.SEDBack.Modelo.Sucursal;
import com.sedback.SEDBack.Persistencia.SucursalRepositorio;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author ususario
 */
@Service
public class SucursalServicio {
    @Autowired
    private SucursalRepositorio repositorio;
    
    public ResponseEntity<HttpMensaje> guardar(Sucursal sucursal){
        try{
            if(StringUtils.isBlank(sucursal.getNombre())){
                return ResponseEntity.badRequest().body(new HttpMensaje("El campo nombre de sucursal no es valido."));
            }
            if(sucursal.getAreas().isEmpty()){
                return ResponseEntity.badRequest().body(new HttpMensaje("Debe seleccionar por los menos 1 área para la sucursal que esta registrando."));
            }
            repositorio.save(sucursal);
            return ResponseEntity.ok().body(new HttpMensaje("La sucursal se ha registrado exitosamente."));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new HttpMensaje("No fue posible registrar la sucursal. Intente nuevamente."));
        }
    }
    
    public ResponseEntity<List<Sucursal>> obtenerSucursales(){
        return ResponseEntity.ok().body(repositorio.findAll());
    }
    
    public ResponseEntity<Sucursal> obtenerSucursalPorId(Integer id){
        return ResponseEntity.ok().body(repositorio.getOne(id));
    }
}
