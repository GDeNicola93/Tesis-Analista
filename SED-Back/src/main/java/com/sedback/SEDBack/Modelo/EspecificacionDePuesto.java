package com.sedback.SEDBack.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.sedback.SEDBack.Views.Views;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"plantillasEvaluacionEnCurso"})
public class EspecificacionDePuesto implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Resumida.class)
    private Integer id;
    
    @OneToMany
    @JsonIgnore
    private List<Objetivo> objetivos;
    
    private String descripcion;
    
    @ManyToOne
    @JsonView(Views.Resumida.class)
    private PuestoTrabajo puesto;
    
    @ManyToOne
    private Sucursal sucursal;
    
    @OneToMany(mappedBy = "especificacionDePuesto")
    @JsonIgnore
    private List<PlantillaEvaluacion> plantillasEvaluacion;
    
    @ManyToMany(mappedBy = "puestosTrabajo")
    @JsonIgnore
    private List<Empleado> empleados;
    
    public List<Objetivo> getObjetivosActivos(){
        List<Objetivo> objetivosEnCurso = new ArrayList<Objetivo>();;
        if(this.getObjetivos() != null){
            this.getObjetivos().stream().filter(obj -> (obj.isEnCurso())).forEachOrdered(obj -> {
                objetivosEnCurso.add(obj);
            });
        }
        return objetivosEnCurso;
    }
    
    public List<PlantillaEvaluacion> getPlantillasEvaluacionEnCurso(){
        List<PlantillaEvaluacion> enCurso = new ArrayList<>();
        for(PlantillaEvaluacion pe : this.plantillasEvaluacion){
            if(pe.isEstaEnCurso()){
                enCurso.add(pe);
            }
        }
        return enCurso;
    }
}
