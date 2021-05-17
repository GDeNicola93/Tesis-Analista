/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sedback.SEDBack.Logica;

import com.sedback.SEDBack.HttpMensajes.EmpleadoIndexDto;
import com.sedback.SEDBack.HttpMensajes.EmpleadoVerDto;
import com.sedback.SEDBack.HttpMensajes.HttpMensaje;
import com.sedback.SEDBack.Mappers.EmpleadoMapper;
import com.sedback.SEDBack.Modelo.Empleado;
import com.sedback.SEDBack.Modelo.Usuario;
import com.sedback.SEDBack.Persistencia.EmpleadoRepositorio;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.Converter;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    
    @Autowired
    private EmpleadoMapper empleadoMapper;
    
    public ResponseEntity<HttpMensaje> verificarCampos(Empleado empleado){
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
            if(empleado.getFechaDeNacimiento().isAfter(LocalDate.now())){
                return ResponseEntity.badRequest().body(new HttpMensaje("La fecha de nacimiento supera la fecha de hoy."));
            }
            if(StringUtils.isBlank(empleado.getDni())){
                return ResponseEntity.badRequest().body(new HttpMensaje("El campo Num DNI no es valido."));
            }else{
                Optional<Empleado> existeDni = repositorio.findByDni(empleado.getDni());
                if (!existeDni.isEmpty()){
                    return ResponseEntity.badRequest().body(new HttpMensaje("El numero de dni ingresado ya existe en la base de datos."));
                }
            }
            if(StringUtils.isBlank(empleado.getEmail())){
                return ResponseEntity.badRequest().body(new HttpMensaje("El campo Email no es valido."));
            }
            if(ObjectUtils.isEmpty(empleado.getPuestosTrabajo())){
                return ResponseEntity.badRequest().body(new HttpMensaje("Debe selecionar como minimo un puesto de trabajo"));
            }
            return ResponseEntity.ok().body(new HttpMensaje("Empleado Registrado Correctamente"));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(new HttpMensaje("Excepción no controlada.Desripción: "+e));
        }
    }
    
    public ResponseEntity<HttpMensaje> guardar(Empleado empleado){
        try{
            repositorio.save(empleado);
            return ResponseEntity.ok().body(new HttpMensaje("Empleado Registrado Correctamente"));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(new HttpMensaje("Excepción no controlada.Desripción: "+e));
        }
    }
    
    public ResponseEntity<List<EmpleadoIndexDto>> getEmpleados(){
        List<Usuario> usuarios = repositorio.getEmpleadosIndex();
        List<EmpleadoIndexDto> empleadosIndexDto = new ArrayList();
        for (int i=0;i<usuarios.size();i++){
            empleadosIndexDto.add(empleadoMapper.toDtoIndex(usuarios.get(i)));
        }
        return ResponseEntity.ok().body(empleadosIndexDto);
    }
    
    public ResponseEntity<EmpleadoVerDto> getEmpleadoById(Integer id){
        Usuario usuario = repositorio.getEmpleadoById(id);
        return ResponseEntity.ok().body(empleadoMapper.toDtoVer(usuario));
    }
    
    public ResponseEntity<List<Empleado>> searchEmpleado(String search){
        return ResponseEntity.ok().body(repositorio.searchEmpleado(search));
    } 
}
