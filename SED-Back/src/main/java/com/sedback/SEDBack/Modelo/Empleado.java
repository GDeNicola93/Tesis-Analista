package com.sedback.SEDBack.Modelo;

import com.sun.istack.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
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
    private Set<EspecificacionDePuesto> puestosTrabajo = new HashSet<>();
    
    public String getFechaDeNacimientoFormateada(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return this.getFechaDeNacimiento().format(formatter);
    }
    
    public String getNombreCompleto(){
        return this.getNombre() + " " + this.getApellido();
    }
}
