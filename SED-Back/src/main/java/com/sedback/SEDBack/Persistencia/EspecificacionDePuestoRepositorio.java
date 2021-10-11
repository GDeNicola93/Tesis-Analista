package com.sedback.SEDBack.Persistencia;

import com.sedback.SEDBack.Dtos.EspecificacionDePuestoIndexDto;
import com.sedback.SEDBack.Dtos.EspecificacionDePuestoVerDto;
import com.sedback.SEDBack.Modelo.EspecificacionDePuesto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecificacionDePuestoRepositorio extends JpaRepository <EspecificacionDePuesto,Integer> {
    
    @Query(value="SELECT edp from EspecificacionDePuesto edp")
    Page<EspecificacionDePuestoIndexDto> obtenerEspecificacionesDePuestosIndex(Pageable pagina);
    
    @Query(value="select edp from EspecificacionDePuesto edp where edp.id = ?1")
    EspecificacionDePuestoVerDto getEspecificacionDePuestById(Integer id);
}
