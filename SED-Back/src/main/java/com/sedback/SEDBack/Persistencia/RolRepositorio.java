package com.sedback.SEDBack.Persistencia;

import com.sedback.SEDBack.Modelo.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepositorio extends JpaRepository<Rol,Integer> {
    
}
