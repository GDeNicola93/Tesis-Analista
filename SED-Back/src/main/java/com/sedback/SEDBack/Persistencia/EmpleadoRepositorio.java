package com.sedback.SEDBack.Persistencia;

import com.sedback.SEDBack.Modelo.Empleado;
import com.sedback.SEDBack.Modelo.Usuario;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepositorio extends JpaRepository<Empleado,Integer>{
    Optional<Empleado> findByDni(String dni);
    
    Optional<Empleado> findByLegajo(String legajo);
    
    Optional<Empleado> findByEmail(String email);
      
    @Query(value="select user from Usuario user join user.empleado emp where emp.id = ?1")
    Usuario getEmpleadoById(Integer id);
    
    @Query(value="SELECT user from Usuario user join user.empleado emp WHERE CONCAT(emp.nombre,' ',emp.apellido) LIKE %?1% OR emp.legajo LIKE %?1% OR emp.dni LIKE %?1%")
    Page<Usuario> getEmpleados(String search,Pageable page);
}
