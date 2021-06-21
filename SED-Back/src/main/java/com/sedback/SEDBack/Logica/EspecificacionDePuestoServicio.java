package com.sedback.SEDBack.Logica;

import com.sedback.SEDBack.Dtos.EspecificacionDePuestoIndexDto;
import com.sedback.SEDBack.Dtos.EspecificacionDePuestoVerDto;
import com.sedback.SEDBack.Dtos.HttpMensaje;
import com.sedback.SEDBack.Mappers.EspecificacionDePuestoDtosMapper;
import com.sedback.SEDBack.Modelo.EspecificacionDePuesto;
import com.sedback.SEDBack.Persistencia.EspecificacionDePuestoRepositorio;
import java.util.List;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EspecificacionDePuestoServicio {
    @Autowired
    private EspecificacionDePuestoRepositorio repositorio;
    
    @Autowired
    private ObjetivoServicio objetivoServicio;
    
    public ResponseEntity<HttpMensaje> guardar(EspecificacionDePuesto edp){
        try{
            if(ObjectUtils.isEmpty(edp.getPuesto())){
                return ResponseEntity.badRequest().body(new HttpMensaje("Debe seleccionar un puesto de trabajo valido."));
            }
            if(ObjectUtils.isEmpty(edp.getSucursal())){
               return ResponseEntity.badRequest().body(new HttpMensaje("Debe seleccionar una sucursal valida.")); 
            }
            if(!ObjectUtils.isEmpty(edp.getObjetivos())){
                this.objetivoServicio.guardar(edp.getObjetivos());
            }
            repositorio.save(edp);
            return ResponseEntity.ok().body(new HttpMensaje("La especificación de puesto de trabajo se ha registrado exitosamente."));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HttpMensaje("Excepción no controlada.Desripción: "+e));
        }
    }
    
    public ResponseEntity<Page<EspecificacionDePuestoIndexDto>> obtenerEspecificacionesDePuestosIndex(Pageable page){
        return ResponseEntity.ok().body(EspecificacionDePuestoDtosMapper.INSTANCE.toEspecificacionDePuestoIndexDtoPage(repositorio.findAll(page)));
    }
    
    public ResponseEntity<List<EspecificacionDePuesto>> getEspecificacionesDePuestosParaSelect(){
        return ResponseEntity.ok().body(repositorio.findAll());
    }
    
    public ResponseEntity<EspecificacionDePuestoVerDto> getEspecificacionesDePuestosById(Integer id){
        return ResponseEntity.ok().body(EspecificacionDePuestoDtosMapper.INSTANCE.toEspecificacionDePuestoVerDto(repositorio.getEspecificacionDePuestById(id)));
    }
}
