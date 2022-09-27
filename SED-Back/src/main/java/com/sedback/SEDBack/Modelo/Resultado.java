package com.sedback.SEDBack.Modelo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data //Agrega las caracteristicas seters y geters, ToString,etc
@NoArgsConstructor
public class Resultado implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private DetallePlantilla detallePlantilla;
    
    @ManyToOne
    private ComportamientoPlantilla comportamientoPlantillaSeleccionado;
    
    private String planAccion;
    
    public Resultado(DetallePlantilla detallePlantilla,ComportamientoPlantilla comportamientoPlantillaSeleccionado){
        this.detallePlantilla = detallePlantilla;
        this.comportamientoPlantillaSeleccionado = comportamientoPlantillaSeleccionado;
    }
    
    public boolean getEsMinimoRequeridoOSupero(){
        int valNumSeleccionada = this.comportamientoPlantillaSeleccionado.getValoracionNumerica();
        int minValNum = this.detallePlantilla.getMinimaValoracionNumerica();
        if(valNumSeleccionada >= minValNum){
            return true;
        }else{
            return false;
        }
    }
}
