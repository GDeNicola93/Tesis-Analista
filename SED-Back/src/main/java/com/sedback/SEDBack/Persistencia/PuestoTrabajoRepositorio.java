package com.sedback.SEDBack.Persistencia;

import com.sedback.SEDBack.Modelo.PuestoTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PuestoTrabajoRepositorio extends JpaRepository<PuestoTrabajo,Integer> {

}
