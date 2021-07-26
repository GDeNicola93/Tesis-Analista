package com.sedback.SEDBack.Validators;

import com.sedback.SEDBack.Logica.AreaServicio;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;


public class AreaUniqueValidator implements ConstraintValidator<AreaUnique, String> {
    @Autowired
    private AreaServicio servicio;

    @Override
    public boolean isValid(String nombreArea, ConstraintValidatorContext context) {
        return servicio.existeArea(nombreArea);
    }
    
    
}
