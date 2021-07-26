package com.sedback.SEDBack.Modelo;


import com.sedback.SEDBack.Validators.AreaUnique;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import com.sun.istack.NotNull;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data //Agrega las caracteristicas seters y geters, ToString,etc
@NoArgsConstructor
public class Area implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotBlank(message = "El nombre del área es requerido.")
    @AreaUnique
    private String nombre;
	
    private String descripcion; 	 
}
