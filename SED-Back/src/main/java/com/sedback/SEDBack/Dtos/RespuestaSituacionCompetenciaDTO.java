
package com.sedback.SEDBack.Dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class RespuestaSituacionCompetenciaDTO {
    private List<PuestoTrabajoSituacionCompetenciaDTO> puestosTrabajo = new ArrayList<>();
    private String competencia;
    
    @JsonFormat(pattern="MMMM YYYY")
    private LocalDate periodo;
    
    public int getTotalEvaluados(){
        int cant = 0;
        for (PuestoTrabajoSituacionCompetenciaDTO p : this.puestosTrabajo){
            cant = cant + p.getTotalEvaluados();
        }
        return cant;
    }
    
    public int getCantNoEvaluados(){
        int cant = 0;
        for (PuestoTrabajoSituacionCompetenciaDTO p : this.puestosTrabajo){
            cant = cant + p.getCantNoEvaluados();
        }
        return cant;
    }
    
    public int getCantSuperaronOalcanzaronMin(){
        int cant = 0;
        for (PuestoTrabajoSituacionCompetenciaDTO p : this.puestosTrabajo){
            cant = cant + p.getCantSuperaronOalcanzaronMin();
        }
        return cant;
    }
    
    public int getCantidadNoAlcanzaronMinimoRequerido(){
        int cant = 0;
        for (PuestoTrabajoSituacionCompetenciaDTO p : this.puestosTrabajo){
            cant = cant + p.getCantidadNoAlcanzaronMinimoRequerido();
        }
        return cant;
    }
    
    public double getPorcentajeNoEvaluados(){
        return ((this.getCantNoEvaluados() * 100) / this.getTotalEvaluados());
    }
    
    public double getPorcentajeNoAlcanzaronMinimoRequerido(){
        return ((this.getCantidadNoAlcanzaronMinimoRequerido() * 100) / this.getTotalEvaluados());
    }
    
    public double getPorcentajeSuperaronOalcanzaronMin(){
        return ((this.getCantSuperaronOalcanzaronMin() * 100) / this.getTotalEvaluados());
    }
    
    public void addPuestoTrabajo(PuestoTrabajoSituacionCompetenciaDTO p){
        this.puestosTrabajo.add(p);
    }
}
