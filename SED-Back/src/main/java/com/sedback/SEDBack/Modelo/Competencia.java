package com.sedback.SEDBack.Modelo;

import com.sedback.SEDBack.Validators.CompetenciaUnique;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data //Agrega las caracteristicas seters y geters, ToString,etc
@NoArgsConstructor
public class Competencia implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String descripcion;
    
    @NotBlank(message = "El nombre de la competencia es requerido.")
    @CompetenciaUnique
    private String nombre;
    
    private boolean enCurso;
}
