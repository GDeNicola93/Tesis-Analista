package com.sedback.SEDBack.Persistencia;

import com.sedback.SEDBack.Modelo.Empleado;
import com.sedback.SEDBack.Modelo.Usuario;
import java.util.List;
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
    
    @Query(value="SELECT * from empleado INNER JOIN usuario on (empleado.id=usuario.empleado_id) INNER JOIN usuario_roles ON (usuario_roles.usuario_id=usuario.id) INNER JOIN rol on (rol.id=usuario_roles.roles_id) where rol.id = 2",nativeQuery = true)
    List<Empleado> getEmpleadosEvaluadores();
    
    @Query("select emp from Empleado emp where emp.dni = ?1")
    List<Empleado> existeNumeroDni(String dni);
    
    @Query("select emp from Empleado emp where emp.email = ?1")
    List<Empleado> existeEmail(String email);
    
    @Query("select emp from Empleado emp where emp.legajo = ?1")
    List<Empleado> existeLegajo(String legajo);
}
