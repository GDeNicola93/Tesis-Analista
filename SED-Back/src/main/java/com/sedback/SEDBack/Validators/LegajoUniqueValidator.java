package com.sedback.SEDBack.Validators;

import com.sedback.SEDBack.Logica.EmpleadoServicio;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;


public class LegajoUniqueValidator implements ConstraintValidator<LegajoUnique, String>{
    @Autowired
    private EmpleadoServicio servicio;
    
    
    @Override
    public boolean isValid(String legajo, ConstraintValidatorContext context) {
        return servicio.existeLegajo(legajo);
    }
    
}
