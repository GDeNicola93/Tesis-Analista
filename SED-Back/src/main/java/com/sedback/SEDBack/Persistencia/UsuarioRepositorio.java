/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sedback.SEDBack.Persistencia;

import com.sedback.SEDBack.Modelo.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ususario
 */
public interface UsuarioRepositorio extends JpaRepository<Usuario,Long>{
    Optional<Usuario> findByNombreUsuario(String nu);
}
