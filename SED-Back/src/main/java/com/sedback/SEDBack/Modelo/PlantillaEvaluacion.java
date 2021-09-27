package com.sedback.SEDBack.Modelo;

import com.fasterxml.jackson.annotation.JsonView;
import com.sedback.SEDBack.Views.Views;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
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
    @JsonView(Views.Resumida.class)
    private Integer id;
    
    @JsonView(Views.Resumida.class)
    private String descripcion;
    
    @ManyToOne
    private EspecificacionDePuesto especificacionDePuesto;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar fechaCreaccion;
    
    private boolean estaEnCurso;
    
    @OneToMany
    private List<DetallePlantilla> detallePlantilla;
    
    public int contarCompetenciasAEvaluar(){
        return this.detallePlantilla.size();
    }
}
