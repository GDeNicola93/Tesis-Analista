package com.sedback.SEDBack.Modelo;

import java.io.Serializable;
import java.time.LocalDate;
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
public class DetalleEvaluacion implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private Empleado evaluado;
    
    private LocalDate fechaRealizacion;
    
    @OneToMany
    private Set<Resultado> resultados = new HashSet<>();
}
