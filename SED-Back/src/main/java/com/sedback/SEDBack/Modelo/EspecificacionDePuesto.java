package com.sedback.SEDBack.Modelo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class EspecificacionDePuesto implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @OneToMany
    private Set<Objetivo> objetivos = new HashSet<>();
    
    private String descripcion;
    
    @ManyToOne
    private PuestoTrabajo puesto;
    
    @ManyToOne
    private Sucursal sucursal;
    
    public Set<Objetivo> getObjetivosEnCurso(){
        Set<Objetivo> objetivosEnCurso = new HashSet<>();
        this.getObjetivos().stream().filter(obj -> (obj.isEnCurso())).forEachOrdered(obj -> {
            objetivosEnCurso.add(obj);
        });
        return objetivosEnCurso;
    }
}
