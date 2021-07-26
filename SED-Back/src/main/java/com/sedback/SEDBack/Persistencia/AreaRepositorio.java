package com.sedback.SEDBack.Persistencia;

import com.sedback.SEDBack.Modelo.Area;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepositorio extends JpaRepository<Area,Integer> {
    
    @Override
    List<Area> findAll();
    
    @Query("select a from Area a where a.nombre = ?1")
    List<Area> existeNombreArea(String nombre);
}
