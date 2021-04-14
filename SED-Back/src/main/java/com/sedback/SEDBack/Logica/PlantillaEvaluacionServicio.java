package com.sedback.SEDBack.Logica;

import com.sedback.SEDBack.HttpMensajes.HttpMensaje;
import com.sedback.SEDBack.Modelo.PlantillaEvaluacion;
import com.sedback.SEDBack.Persistencia.PlantillaEvaluacionRepositorio;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class PlantillaEvaluacionServicio {
    @Autowired
    private PlantillaEvaluacionRepositorio repositorio;
    
    @Autowired
    private CompetenciaServicio competenciaServicio;
    
    @Autowired
    private ComportamientoPlantillaServicio comportamientoPlantillaServicio;
    
    @Autowired
    private DetallePlantillaServicio detallePlantillaServicio;
    
    public ResponseEntity<HttpMensaje> guardar(PlantillaEvaluacion pe){
        try{
            if(pe.getPuestoTrabajo() == null){
                return ResponseEntity.badRequest().body(new HttpMensaje("Debe seleccionar un puesto de trabajo valido."));
            }
            if(pe.getDetallePlantilla().isEmpty()){
                return ResponseEntity.badRequest().body(new HttpMensaje("Debe agregar como minimo una competencia."));
            }
            //Guardo las competencias que tiene la plantilla de evaluación antes de guardar la plantilla de evaluación
            this.competenciaServicio.guardarCompetencias(pe.getDetallePlantilla());
            
            //Ahora se deben guardar los Comportamientos de plantilla
            this.comportamientoPlantillaServicio.guardarComportamientoPlantilla(pe.getDetallePlantilla());
            
            //Luego debo guardar los detalles de plantilla;
            this.detallePlantillaServicio.guardarDetallePlantilla(pe.getDetallePlantilla());
            
            //Y por ultimo guardo la nueva Plantilla de Evaluación
            pe.setEstaEnCurso(true);
            pe.setFechaCreaccion(Calendar.getInstance());
            repositorio.save(pe);
            return ResponseEntity.ok().body(new HttpMensaje("Plantilla registrada exitosamente."));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(new HttpMensaje("No fue posible registrar la plantilla de evaluación. Intente nuevamente."));
        }
    }
    
    public ResponseEntity<Page<PlantillaEvaluacion>> obtenerPlantillas(Pageable pagina){
        return ResponseEntity.ok().body(repositorio.findAll(pagina));
    }
    
}
