package com.sedback.SEDBack.Modelo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate fechaInicioEvaluacion;
    
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate fechaFinEvaluacion;
    
    @JsonFormat(pattern="dd-MM-yyyy HH:mm")
    private LocalDateTime fechaHoraCreacion;
    
    @ManyToOne
    private Estado estado;
    
    @ManyToOne
    private Empleado evaluador;
    
    @ManyToOne
    private PlantillaEvaluacion plantillaEvaluacion;
    
    @OneToMany(mappedBy = "evaluacion")
    private List<DetalleEvaluacion> detalleEvaluacion;
    
    public boolean enCurso(LocalDate fechaActual,Estado enCurso){ //Este metodo recibe el estado EnCurso y la fecha Actual
        if(fechaActual.equals(this.getFechaInicioEvaluacion())){
            this.setEstado(enCurso);
            return true;
        }
        return false;
    }
    
    public boolean finalizar(LocalDate fechaActual,Estado finalizar){ //Este metodo recibe el estado Finalizar y la fecha Actual
        if(fechaActual.equals(this.getFechaFinEvaluacion())){
            this.setEstado(finalizar);
            return true;
        }
        return false;
    }
    
    //Si ya fueron evaluados todos los empleados se finaliza automaticamente sin esperar a la fecha de finalización.
    public boolean terminoDeEvaluar(){
        if(this.getEstaParaEvaluar() && (this.getCantidadEmpleadosAEvaluar() - this.getCantidadEmpleadosEvaluados()) == 0){
            return true;
        }
        return false;
    }
    
    public List<DetalleEvaluacion> crearDetallesEvaluacion(List<Empleado> empleadosAEvaluar){
        List<DetalleEvaluacion> detallesEvaluacion = new ArrayList<>();
        for(Empleado emp : empleadosAEvaluar){
            DetalleEvaluacion nuevoDetalle = new DetalleEvaluacion();
            nuevoDetalle.setEvaluado(emp);
            nuevoDetalle.setEvaluacion(this);
            detallesEvaluacion.add(nuevoDetalle);
        }
        return detallesEvaluacion;
    }
    
    public Integer getCantidadEmpleadosAEvaluar(){
        return this.getDetalleEvaluacion().size();
    }
    
    public Integer getCantidadEmpleadosEvaluados(){
        Integer cantidadEmpleadosEvaluados = 0;
        for(DetalleEvaluacion de : this.getDetalleEvaluacion()){
            if(de.getFueEvaluado()){
                cantidadEmpleadosEvaluados = cantidadEmpleadosEvaluados + 1;
            }
        }
        return cantidadEmpleadosEvaluados;
    }
    
    public Integer getRestantesAEvaluar(){
        return this.getCantidadEmpleadosAEvaluar() - this.getCantidadEmpleadosEvaluados();
    }
    
    public Integer getPorcentajeEvaluados(){
        return (this.getCantidadEmpleadosEvaluados() * 100) / this.getCantidadEmpleadosAEvaluar();
    }
    
    public Integer getPorcentajeNoEvaluados(){
        return (this.getRestantesAEvaluar() * 100) / this.getCantidadEmpleadosAEvaluar();
    }
    
    public boolean cancelar(Estado cancelado){
        if(this.getEstado().getId() == 1){
            this.setEstado(cancelado);
            return true;
        }
        return false;
    }
    
    public boolean getEsCancelable(){
        if(this.getEstado().getId() == 1){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean getEsEditable(){
        if(this.getEstado().getId() == 1 || this.getEstado().getId() == 2){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean getEstaParaEvaluar(){
        if(this.getEstado().getId() == 2){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean esDeEvaluador(Empleado emp){
        if(this.getEvaluador().equals(emp)){
            return true;
        }
        return false;
    }
}
