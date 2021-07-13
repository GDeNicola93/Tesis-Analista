package com.sedback.SEDBack.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.sedback.SEDBack.Views.Views;
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
    @JsonView(Views.Resumida.class)
    private Integer id;
    
    @OneToMany
    private List<Objetivo> objetivos;
    
    private String descripcion;
    
    @ManyToOne
    @JsonView(Views.Resumida.class)
    private PuestoTrabajo puesto;
    
    @ManyToOne
    private Sucursal sucursal;
    
    @JsonIgnore
    public List<Objetivo> getObjetivosEnCurso(){
        List<Objetivo> objetivosEnCurso = new ArrayList<Objetivo>();;
        this.getObjetivos().stream().filter(obj -> (obj.isEnCurso())).forEachOrdered(obj -> {
            objetivosEnCurso.add(obj);
        });
        return objetivosEnCurso;
    }
    
//    public boolean esDeSucursal(Sucursal s){
//        return this.getSucursal().equals(s);
//    }
}
