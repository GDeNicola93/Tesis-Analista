package com.sedback.SEDBack.Modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
@Data //Agrega las caracteristicas seters y geters, ToString,etc
@NoArgsConstructor
public class Evaluacion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private LocalDate fechaInicioEvaluacion;
    
    private LocalDate fechaFinEvaluacion;
    
    private LocalDateTime fechaHoraCreacion;
    
    @ManyToOne
    private Estado estado;
    
    @ManyToOne
    private Empleado evaluador;
    
    @ManyToOne
    private PlantillaEvaluacion plantillaEvaluacion;
    
    private Integer puntajeMinAprobacion;
    
    @OneToMany
    private List<DetalleEvaluacion> detalleEvaluacion = new ArrayList<DetalleEvaluacion>();
    
    public void enCurso(LocalDate fechaActual,Estado enCurso){ //Este metodo recibe el estado EnCurso y la fecha Actual
        if(fechaActual.equals(this.getFechaInicioEvaluacion())){
            this.setEstado(enCurso);
        }
    }
    
    public void finalizar(LocalDate fechaActual,Estado finalizar){ //Este metodo recibe el estado EnCurso y la fecha Actual
        if(fechaActual.equals(this.getFechaFinEvaluacion())){
            this.setEstado(finalizar);
        }
    }
    
    private void agregarDetalleEvaluacion(DetalleEvaluacion detalle){
        this.detalleEvaluacion.add(detalle);
    }
    
    public List<DetalleEvaluacion> crearDetallesEvaluacion(List<Empleado> empleadosAEvaluar){
        for(Empleado emp : empleadosAEvaluar){
            DetalleEvaluacion nuevoDetalle = new DetalleEvaluacion();
            nuevoDetalle.setEvaluado(emp);
            this.agregarDetalleEvaluacion(nuevoDetalle);
        }
        return this.getDetalleEvaluacion();
    }
}
