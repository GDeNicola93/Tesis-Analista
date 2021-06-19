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
public class Evaluacion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar fechaInicioEvaluacion;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar fechaFinEvaluacion;
    
    @ManyToOne
    private Estado estado;
    
    @ManyToOne
    private Empleado evaluador;
    
    @ManyToOne
    private PlantillaEvaluacion plantillaEvaluacion;
    
    private Integer puntajeMinAprobacion;
    
    @OneToMany
    private Set<DetalleEvaluacion> detalleEvaluacion = new HashSet<>();
}
