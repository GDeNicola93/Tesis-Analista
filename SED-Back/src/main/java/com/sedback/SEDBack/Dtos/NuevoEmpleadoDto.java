package com.sedback.SEDBack.Dtos;

import com.sedback.SEDBack.Modelo.Empleado;
import com.sedback.SEDBack.Modelo.EspecificacionDePuesto;
import com.sedback.SEDBack.Modelo.Rol;
import com.sedback.SEDBack.Modelo.Usuario;
import com.sedback.SEDBack.Validators.DniUnique;
import com.sedback.SEDBack.Validators.EmailUnique;
import com.sedback.SEDBack.Validators.LegajoUnique;
import com.sedback.SEDBack.Validators.UsuarioUnique;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class NuevoEmpleadoDto {
    
    //Atributos del empleado
    @NotBlank(message = "El legajo es requerido.")
    @LegajoUnique
    private String legajo;
    
    @NotBlank(message = "El nombre del empleado es requerido.")
    private String nombre;
    
    @NotBlank(message = "El apellido del empleado es requerido.")
    private String apellido;
    
    @NotNull(message = "La fecha de nacimiento del empleado es requerida.")
    @Past(message = "La fecha de nacimiento del empleado debe ser anterior a la fecha actual.")
    private LocalDate fechaDeNacimiento;
    
    @NotBlank(message = "El dni del empleado es requerido.")
    @Size(min = 8, max = 8, message = "El número de dni debe tener 8 (ocho) digitos.")
    @DniUnique
    private String dni;
    
    @NotBlank(message = "El email del empleado es requerido.")
    @Email(message = "El formato del email ingresado no es valido.")
    @EmailUnique
    private String email;
    
    @NotEmpty(message = "Debe seleccionar como minimo un puesto de trabajo para el empleado.")
    private List<EspecificacionDePuesto> puestosTrabajo;
    
    //Atributos del usuario
    @NotBlank(message = "El nombre de usuario es requerido.")
    @UsuarioUnique
    private String nombreUsuario;
    
    @NotBlank(message = "El password de usuario es requerido.")
    private String password;
    
    @NotBlank(message = "La repetición del password de usuario es requerida.")
    private String repeatPassword;
    
    @NotEmpty(message = "Debe seleccionar como minimo un perfil de usuario para el empleado.")
    private Set<Rol> roles;
    
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
