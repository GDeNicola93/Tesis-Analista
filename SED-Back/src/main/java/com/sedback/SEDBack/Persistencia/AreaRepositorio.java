package com.sedback.SEDBack.Persistencia;

import com.sedback.SEDBack.Modelo.Area;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepositorio extends JpaRepository<Area,Integer> {
    
    @Override
    List<Area> findAll();
}
