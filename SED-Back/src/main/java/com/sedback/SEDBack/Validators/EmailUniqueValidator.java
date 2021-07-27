package com.sedback.SEDBack.Validators;

import com.sedback.SEDBack.Logica.EmpleadoServicio;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;


public class EmailUniqueValidator implements ConstraintValidator<EmailUnique, String>{
    @Autowired
    private EmpleadoServicio servicio;
    
    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return servicio.existeEmail(email);
    }
    
}
