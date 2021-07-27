package com.sedback.SEDBack.Validators;

import com.sedback.SEDBack.Logica.EmpleadoServicio;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;


public class DniUniqueValidator implements ConstraintValidator<DniUnique, String>{
    @Autowired
    private EmpleadoServicio servicio;
    
    @Override
    public boolean isValid(String dni, ConstraintValidatorContext context) {
        return servicio.existeNumeroDni(dni);
    }
    
}
