/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sedback.SEDBack.Persistencia;

import com.sedback.SEDBack.Modelo.Area;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ususario
 */
public interface AreaRepositorio extends JpaRepository<Area,Integer> {
    
    @Override
    List<Area> findAll();
}
