package com.sedback.SEDBack.Modelo;

import com.fasterxml.jackson.annotation.JsonView;
import com.sedback.SEDBack.Views.Views;
import com.sun.istack.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
    
    @NotNull
    @Column(unique = false)
    @JsonView(Views.Resumida.class)
    private String nombrePuesto;
    
    @NotNull
    @ManyToOne
    private Area area;
}
