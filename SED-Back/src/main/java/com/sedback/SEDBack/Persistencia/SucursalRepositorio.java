package com.sedback.SEDBack.Persistencia;

import com.sedback.SEDBack.Modelo.Sucursal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SucursalRepositorio extends JpaRepository<Sucursal,Integer> {
    
    @Override
    List<Sucursal> findAll();
}
