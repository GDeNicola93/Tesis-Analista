package com.sedback.SEDBack.Modelo;

import com.sun.istack.Nullable;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data //Agrega las caracteristicas seters y geters, ToString,etc
@NoArgsConstructor
public class DetallePlantilla implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
   
    @OneToOne
    private Competencia competencia;
    
    private boolean esPreguntaObjetivo;
    
    @Nullable
    @OneToOne
    private Objetivo obj;
    
    private String gradoMinimoRequerido;
    
    @OneToMany
    private List<ComportamientoPlantilla> comportamiento;
    
    public int getMinimaValoracionNumerica(){
        for(ComportamientoPlantilla c : this.comportamiento){
            if(c.getGrado().equals(this.gradoMinimoRequerido)){
                return c.getValoracionNumerica();
            }
        }
        return 0;
    }
    
    //Se sobreescribe al de lombook para devolver los comportamientos ordenados por grado
    public List<ComportamientoPlantilla> getComportamiento(){
        List<ComportamientoPlantilla> comportamientos = this.comportamiento;
    	
    	Comparator<ComportamientoPlantilla> comparator = new Comparator<ComportamientoPlantilla>() {
            public int compare( ComportamientoPlantilla com1, ComportamientoPlantilla com2 ) {
                int resultado = com1.getGrado().compareTo(com2.getGrado());
                if ( resultado != 0 ) {
                    return resultado;
                }
                return resultado;
            }
        };
        Collections.sort( comportamientos, comparator );
        return comportamientos;
    }
    
}
