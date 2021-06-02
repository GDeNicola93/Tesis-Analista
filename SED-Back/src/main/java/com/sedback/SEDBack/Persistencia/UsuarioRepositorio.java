package com.sedback.SEDBack.Persistencia;

import com.sedback.SEDBack.Modelo.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,Long>{
    Optional<Usuario> findByNombreUsuario(String nu);
}
