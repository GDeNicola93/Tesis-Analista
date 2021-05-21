/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sedback.SEDBack.Persistencia;

import com.sedback.SEDBack.HttpMensajes.EmpleadoIndexDto;
import com.sedback.SEDBack.Modelo.Empleado;
import com.sedback.SEDBack.Modelo.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author ususario
 */
public interface EmpleadoRepositorio extends JpaRepository<Empleado,Integer>{
    Optional<Empleado> findByDni(String dni);
    
    Optional<Empleado> findByLegajo(String legajo);
    
    Optional<Empleado> findByEmail(String email);
    
    @Query(value="select * from empleado where CONCAT(nombre,' ',apellido) LIKE %?1% OR dni LIKE %?1% OR "
            + "legajo LIKE %?1%",nativeQuery = true)
    List<Empleado> searchEmpleado(String search);
    
//    @Query(value="select new com.sedback.SEDBack.HttpMensajes.EmpleadoIndexDto(emp.id,emp.legajo,emp.nombre,emp.apellido,user.habilitado,sucursal) "
//            +"from Usuario user JOIN user.empleado emp JOIN emp.puestosTrabajo puestos JOIN puestos.sucursal sucursal")
    @Query(value="select user from Usuario user")
    List<Usuario> getEmpleadosIndex();
    
    @Query(value="select user from Usuario user join user.empleado emp where emp.id = ?1")
    Usuario getEmpleadoById(Integer id);
}
