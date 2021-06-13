package com.sedback.SEDBack.Logica;

import com.sedback.SEDBack.Dtos.HttpMensaje;
import com.sedback.SEDBack.Modelo.PuestoTrabajo;
import com.sedback.SEDBack.Persistencia.PuestoTrabajoRepositorio;
import java.util.List;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PuestoTrabajoServicio {
    @Autowired
    private PuestoTrabajoRepositorio repositorio;
    
    public ResponseEntity<HttpMensaje> guardar(PuestoTrabajo puestoTrabajo){
        try{
            if(StringUtils.isBlank(puestoTrabajo.getNombrePuesto())){
                return ResponseEntity.badRequest().body(new HttpMensaje("El campo nombre de puesto no es valido."));
            }
            if(ObjectUtils.isEmpty(puestoTrabajo.getArea())){
                return ResponseEntity.badRequest().body(new HttpMensaje("Debe seleccionar un área valida."));
            }
            repositorio.save(puestoTrabajo);
            return ResponseEntity.ok().body(new HttpMensaje("El puesto de trabajo se ha registrado exitosamente."));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(new HttpMensaje("No fue posible guardar el puesto de trabajo. Intente nuevamente."));
        }
    }
    
    public ResponseEntity<List<PuestoTrabajo>> getPuestosTrabajoSelect(){
        return ResponseEntity.ok().body(repositorio.findAll());
    }
    
}
