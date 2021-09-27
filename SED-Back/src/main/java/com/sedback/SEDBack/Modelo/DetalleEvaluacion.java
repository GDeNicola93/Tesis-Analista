package com.sedback.SEDBack.Modelo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sedback.SEDBack.Dtos.ResultadoDto;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
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
    
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate fechaRealizacion;
    
    @OneToMany(cascade = { CascadeType.ALL }) //Al agregar este CascadeType.ALL al momento de crear un nuevo detalle no hace falta gurdar antes las intancias de Resultado.Por lo que al guardar un DetalleEvaluacion se estarian guardando tambien los Resultados.
    private List<Resultado> resultados;
    
    @ManyToOne
    @JsonIgnore
    private Evaluacion evaluacion;
    
    public boolean getFueEvaluado(){
        if(fechaRealizacion == null){
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
    
    public boolean getAprobado(){
        if(this.getCalificacion() >= evaluacion.getPuntajeMinAprobacion()){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean esDeEvaluado(Empleado emp){
        if(this.getEvaluado().equals(emp)){
            return true;
        }
        return false;
    }
    
    public boolean esDeEvaluador(Empleado emp){
        if(this.getEvaluacion().esDeEvaluador(emp)){
            return true;
        }else{
            return false;
        }
    }
    
    public void crearResultado(ResultadoDto resultadoDto){
        Resultado nuevoResultado = new Resultado(resultadoDto.getDetallePlantilla(),resultadoDto.getComportamientoPlantillaSeleccionado());
        this.getResultados().add(nuevoResultado);
    }            
}
