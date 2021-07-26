package com.sedback.SEDBack.Modelo;

import com.fasterxml.jackson.annotation.JsonView;
import com.sedback.SEDBack.Views.Views;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class PuestoTrabajo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Resumida.class)
    private Integer id;
    
    @JsonView(Views.Resumida.class)
    @NotBlank(message = "El nombre del puesto de trabajo es requerido.")
    private String nombrePuesto;
    
    @ManyToOne
    @NotNull(message = "Debe seleccionar una área valida.")
    private Area area;
}
