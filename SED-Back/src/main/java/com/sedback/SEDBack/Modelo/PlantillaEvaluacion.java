package com.sedback.SEDBack.Modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data //Agrega las caracteristicas seters y geters, ToString,etc
@NoArgsConstructor
public class PlantillaEvaluacion implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String descripcion;
    @ManyToOne
    private PuestoTrabajo puestoTrabajo;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar fechaCreaccion;
    
    private boolean estaEnCurso;
    
    @OneToMany
    private Set<DetallePlantilla> detallePlantilla = new HashSet<>();
}
