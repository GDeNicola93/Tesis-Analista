package com.sedback.SEDBack.Modelo;

import com.sedback.SEDBack.Enums.RolNombre;
import com.sun.istack.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Usuario implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombreUsuario;
    
    private String password;
    
    private boolean habilitado;
    
    @ManyToMany
    private Set<Rol> roles = new HashSet<>();
    
    @OneToOne
    private Empleado empleado;
    
    private String nombreFotoPerfil;
    
    public boolean esAdministrador(){
        for(Rol r : this.getRoles()){
            if(r.getRolNombre() == RolNombre.Administrador){
                return true;
            }
        }
        return false;
    }
    
    public boolean esEvaluador(){
        for(Rol r : this.getRoles()){
            if(r.getRolNombre() == RolNombre.Evaluador){
                return true;
            }
        }
        return false;
    }
    
    public boolean esEmpleado(){
        for(Rol r : this.getRoles()){
            if(r.getRolNombre() == RolNombre.Empleado){
                return true;
            }
        }
        return false;
    }
}
