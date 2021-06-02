package com.sedback.SEDBack.Persistencia;

import com.sedback.SEDBack.Modelo.PuestoTrabajo;
import com.sedback.SEDBack.Modelo.Sucursal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PuestoTrabajoRepositorio extends JpaRepository<PuestoTrabajo,Integer> {
    List<PuestoTrabajo> findBySucursal(Sucursal sucursal);
    
    List<PuestoTrabajo> findBySucursalId(Integer id);
}
