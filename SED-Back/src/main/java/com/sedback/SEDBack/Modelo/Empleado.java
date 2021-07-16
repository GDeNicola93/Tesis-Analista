package com.sedback.SEDBack.Modelo;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sedback.SEDBack.Views.Views;
import com.sun.istack.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
public class Empleado implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Resumida.class)
    private Integer id;
    
    @NotNull
    @Column(unique = true)
    private String legajo;
    
    @NotNull
    private String nombre;
    
    @NotNull
    private String apellido;
    
    @NotNull
    private String dni;
    
    @NotNull
    private String email;
    
    @NotNull
    private LocalDate fechaDeNacimiento;
    
    @NotNull
    @ManyToMany
    private List<EspecificacionDePuesto> puestosTrabajo;
    
    public String getFechaDeNacimientoFormateada(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return this.getFechaDeNacimiento().format(formatter);
    }
    
    @JsonView(Views.Resumida.class)
    public String getNombreCompleto(){
        return this.getNombre() + " " + this.getApellido();
    }
}
