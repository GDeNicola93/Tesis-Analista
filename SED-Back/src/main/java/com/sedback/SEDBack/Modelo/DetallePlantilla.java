package com.sedback.SEDBack.Modelo;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data //Agrega las caracteristicas seters y geters, ToString,etc
@NoArgsConstructor
public class DetallePlantilla implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
   
    @OneToOne
    private Competencia competencia;
    
    private boolean esPreguntaObjetivo;
    
    @Nullable
    @OneToOne
    private Objetivo obj;
    
    private int puntajeMinAprobacion;
    
    @OneToMany
    private Set<ComportamientoPlantilla> comportamiento = new HashSet<>();
    
}
