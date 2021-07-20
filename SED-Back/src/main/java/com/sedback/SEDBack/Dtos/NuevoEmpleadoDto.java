package com.sedback.SEDBack.Dtos;

import com.sedback.SEDBack.Modelo.Empleado;
import com.sedback.SEDBack.Modelo.EspecificacionDePuesto;
import com.sedback.SEDBack.Modelo.Rol;
import com.sedback.SEDBack.Modelo.Usuario;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Data;

@Data
public class NuevoEmpleadoDto {
    
    //Atributos del empleado
    private String legajo;
    private String nombre;
    private String apellido;
    private LocalDate fechaDeNacimiento;
    private String dni;
    private String email;
    private List<EspecificacionDePuesto> puestosTrabajo;
    
    //Atributos del usuario
    private String nombreUsuario;
    private String password;
    private String repeatPassword;
    private Set<Rol> roles = new HashSet<>();
    
    public Empleado getEmpleado(){
        Empleado emp = new Empleado();
        emp.setLegajo(legajo);
        emp.setNombre(nombre);
        emp.setApellido(apellido);
        emp.setFechaDeNacimiento(fechaDeNacimiento);
        emp.setDni(dni);
        emp.setEmail(email);
        emp.setPuestosTrabajo(puestosTrabajo);
        return emp;
    }
    
    public Usuario getUsuario(){
        Usuario user = new Usuario();
        user.setNombreUsuario(nombreUsuario);
        user.setPassword(password);
        user.setRoles(roles);
        user.setEmpleado(this.getEmpleado());
        return user;
    } 
}
