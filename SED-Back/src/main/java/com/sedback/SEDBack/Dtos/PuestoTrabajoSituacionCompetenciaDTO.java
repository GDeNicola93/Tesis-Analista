
package com.sedback.SEDBack.Dtos;

import lombok.Data;

@Data
public class PuestoTrabajoSituacionCompetenciaDTO {
    private String nombrePuesto;
    private String sucursal;
    private int cantSuperaronOalcanzaronMin;
    private int cantidadNoAlcanzaronMinimoRequerido;
    private int cantNoEvaluados;
    
    //Devuelve todos los empleados que fueron evaluados o no
    //Ej Se evaluaron 5 y no se evaluo 1 -> Devulve 6
    public int getTotalEvaluados(){
        return this.cantNoEvaluados + this.cantidadNoAlcanzaronMinimoRequerido + this.cantSuperaronOalcanzaronMin;
    }
    
    public double getPorcentajeNoEvaluados(){
        return ((this.cantNoEvaluados * 100) / this.getTotalEvaluados());
    }
    
    public double getPorcentajeNoAlcanzaronMinimoRequerido(){
        return ((this.cantidadNoAlcanzaronMinimoRequerido * 100) / this.getTotalEvaluados());
    }
    
    public double getPorcentajeSuperaronOalcanzaronMin(){
        return ((this.cantSuperaronOalcanzaronMin * 100) / this.getTotalEvaluados());
    }
}
