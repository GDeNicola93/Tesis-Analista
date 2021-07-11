package com.sedback.SEDBack.Modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
    private List<Objetivo> objetivos;
    
    private String descripcion;
    
    @ManyToOne
    private PuestoTrabajo puesto;
    
    @ManyToOne
    private Sucursal sucursal;
    
    public List<Objetivo> getObjetivosEnCurso(){
        List<Objetivo> objetivosEnCurso = new ArrayList<Objetivo>();;
        this.getObjetivos().stream().filter(obj -> (obj.isEnCurso())).forEachOrdered(obj -> {
            objetivosEnCurso.add(obj);
        });
        return objetivosEnCurso;
    }
    
    public boolean esDeSucursal(Sucursal s){
        return this.getSucursal().equals(s);
    }
}
