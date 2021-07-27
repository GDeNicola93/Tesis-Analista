package com.sedback.SEDBack.Validators;

import com.sedback.SEDBack.Logica.UsuarioServicio;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;


public class UsuarioUniqueValidator implements ConstraintValidator<UsuarioUnique, String> {
    @Autowired
    private UsuarioServicio servicio;

    @Override
    public boolean isValid(String nombreUsuario, ConstraintValidatorContext context) {
        return this.servicio.existeNombreUsuario(nombreUsuario);
    }
    
}
