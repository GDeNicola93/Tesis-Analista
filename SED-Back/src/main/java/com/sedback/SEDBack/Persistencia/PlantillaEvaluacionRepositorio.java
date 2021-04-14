package com.sedback.SEDBack.Persistencia;

import com.sedback.SEDBack.Modelo.PlantillaEvaluacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface PlantillaEvaluacionRepositorio extends JpaRepository<PlantillaEvaluacion,Integer> {
    
//    @Query(value="select plantilla_evaluacion.* from plantilla_evaluacion inner join plantilla_evaluacion_competencia on (plantilla_evaluacion_competencia.plantilla_evaluacion_id=plantilla_evaluacion.id) inner join detalle_plantilla on (plantilla_evaluacion_competencia.competencia_id=detalle_plantilla.id) inner join detalle_plantilla_comportamiento on (detalle_plantilla_comportamiento.detalle_plantilla_id = detalle_plantilla.id) inner join comportamiento_plantilla on (comportamiento_plantilla.id=detalle_plantilla_comportamiento.comportamiento_id)\n" +
//"group by plantilla_evaluacion.id order by comportamiento_plantilla.valoracion_numerica",nativeQuery = true)
//    @Override
//    Page<PlantillaEvaluacion> findAll(Pageable pagina);
}
