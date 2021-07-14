package com.sedback.SEDBack.Logica;

import com.sedback.SEDBack.Dtos.EmpleadoIndexDto;
import com.sedback.SEDBack.Dtos.EmpleadoVerDto;
import com.sedback.SEDBack.Dtos.HttpMensaje;
import com.sedback.SEDBack.Mappers.EmpleadoIndexDtoMapper;
import com.sedback.SEDBack.Mappers.EmpleadoVerDtoMapper;
import com.sedback.SEDBack.Modelo.Empleado;
import com.sedback.SEDBack.Persistencia.EmpleadoRepositorio;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class EmpleadoServicio {
    @Autowired
    private EmpleadoRepositorio repositorio;
    
    public ResponseEntity<HttpMensaje> verificarCampos(Empleado empleado){
        try{
            //Primero verifico que sea correcta la información del empleado
            if(StringUtils.isBlank(empleado.getLegajo())){
                return ResponseEntity.badRequest().body(new HttpMensaje("El campo legajo no es valido."));
            }else{
                Optional<Empleado> existeLegajo = repositorio.findByLegajo(empleado.getLegajo());
                if(!existeLegajo.isEmpty()){
                    return ResponseEntity.badRequest().body(new HttpMensaje("El legajo ingresado ya se encuentra asignado a otro empleado"));
                }
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
            }else{
                Optional<Empleado> existeEmail = repositorio.findByEmail(empleado.getEmail());
                if (!existeEmail.isEmpty()){
                    return ResponseEntity.badRequest().body(new HttpMensaje("El email ingresado ya esta asignado a otro empleado."));
                }
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
    
    public ResponseEntity<EmpleadoVerDto> getEmpleadoById(Integer id){
        return ResponseEntity.ok().body(EmpleadoVerDtoMapper.INSTANCE.empleadotoEmpleadoVerDto(repositorio.getEmpleadoById(id)));
    }
    
    public ResponseEntity<Page<EmpleadoIndexDto>> getEmpleados(String filtro,Pageable page){
        return ResponseEntity.ok().body(EmpleadoIndexDtoMapper.INSTANCE.toEmpleadoIndexDtoPage(repositorio.getEmpleados(filtro, page)));
    }
    
    public ResponseEntity<List<Empleado>> getEmpleadosEvaluadores(){
        return ResponseEntity.ok().body(repositorio.getEmpleadosEvaluadores());
    }
}
