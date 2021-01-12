/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sedback.SEDBack.Logica;

import com.sedback.SEDBack.HttpMensajes.HttpMensaje;
import com.sedback.SEDBack.Modelo.PuestoTrabajo;
import com.sedback.SEDBack.Persistencia.PuestoTrabajoRepositorio;
import java.util.List;
import org.apache.commons.lang3.ObjectUtils;
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
    
    @Autowired
    private ObjetivoServicio objetivoServicio;
    
    public ResponseEntity<HttpMensaje> guardar(PuestoTrabajo puestoTrabajo){
        try{
            if(StringUtils.isBlank(puestoTrabajo.getNombrePuesto())){
                return ResponseEntity.badRequest().body(new HttpMensaje("El campo nombre de puesto no es valido."));
            }
            if(ObjectUtils.isEmpty(puestoTrabajo.getSucursal())){
                return ResponseEntity.badRequest().body(new HttpMensaje("Debe seleccionar una sucursal valida."));
            }
            if(ObjectUtils.isEmpty(puestoTrabajo.getArea())){
                return ResponseEntity.badRequest().body(new HttpMensaje("Debe seleccionar un área valida."));
            }
            if(puestoTrabajo.getObjetivosPuesto().isEmpty()){
                return ResponseEntity.badRequest().body(new HttpMensaje("Debe agregar por lo menos un objetivo al puesto a crear."));
            }
            
            //Antes de guardar el puesto debo guardar primero los objetivos de este
            if(objetivoServicio.guardar((puestoTrabajo.getObjetivosPuesto()))){
                repositorio.save(puestoTrabajo);
                return ResponseEntity.ok().body(new HttpMensaje("El puesto de trabajo se ha registrado exitosamente."));
            }else{
                return ResponseEntity.badRequest().body(new HttpMensaje("No fue posible guardar los objetivos. Intente de nuevo."));
            }
        }catch(Exception e){
            return ResponseEntity.badRequest().body(new HttpMensaje("No fue posible guardar el puesto de trabajo. Intente nuevamente."));
        }
    }
    
    public ResponseEntity<List<PuestoTrabajo>> obtenerPuestosTrabajo(){
        return ResponseEntity.ok().body(repositorio.findAll());
    }
    
}
