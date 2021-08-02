package com.sedback.SEDBack.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.LocalDate;
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
    private List<Resultado> resultados;
    
    public boolean getFueEvaluado(){
        if(this.getFechaRealizacion() == null){
            return false;
        }
        return true;
    }
    
    public Integer getCalificacion(){
        Integer calificacion = 0;
        for(Resultado r : resultados){
            calificacion = calificacion + r.getResultadoObtenidoCompetencia();
        }
        return calificacion;
    }
    
    public boolean getAprobado(Integer puntajeMinAprobacion){
        if(this.getCalificacion() >= puntajeMinAprobacion){
            return true;
        }else{
            return false;
        }
    }
}
