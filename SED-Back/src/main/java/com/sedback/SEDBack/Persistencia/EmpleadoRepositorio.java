/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sedback.SEDBack.Persistencia;

import com.sedback.SEDBack.Modelo.Empleado;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author ususario
 */
public interface EmpleadoRepositorio extends JpaRepository<Empleado,Integer>{
    Optional<Empleado> findByDni(String dni);
    
    @Query(value="select * from empleado where CONCAT(nombre,' ',apellido) LIKE %?1% OR dni LIKE %?1% OR "
            + "legajo LIKE %?1%",nativeQuery = true)
    List<Empleado> searchEmpleado(String search);
}
