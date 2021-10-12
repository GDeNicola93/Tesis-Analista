package com.sedback.SEDBack.Modelo;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sedback.SEDBack.Views.Views;
import com.sun.istack.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
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
    @JsonView(Views.Resumida.class)
    private Integer id;
   
    private String legajo;
    
    private String nombre;
    
    private String apellido;
    
    private String dni;
    
    private String email;
    
    private LocalDate fechaDeNacimiento;
    
    @ManyToMany
    private List<EspecificacionDePuesto> puestosTrabajo;
    
    public Set<String> getNombreSucursalesDondeTrabaja(){
        Set<String> nombreSucursalesTrabajo = new HashSet<>();
        for (EspecificacionDePuesto p : this.puestosTrabajo) {
            nombreSucursalesTrabajo.add(p.getSucursal().getNombre());
        }
        return nombreSucursalesTrabajo;
    }
    
    public Set<String> getNombrePuestosTrabajoConNombreSucursal(){
        Set<String> puestosTrabajoToString = new HashSet<>();
        for (EspecificacionDePuesto p : this.puestosTrabajo) {
            puestosTrabajoToString.add(p.getPuesto().getNombrePuesto() + "(" + p.getSucursal().getNombre() + ")");
        }
        return puestosTrabajoToString;
    }
    
    @JsonView(Views.Resumida.class)
    public String getNombreCompleto(){
        return this.getNombre() + " " + this.getApellido();
    }
}
