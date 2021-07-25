
package com.sedback.SEDBack.Validators;

import com.sedback.SEDBack.Logica.CompetenciaServicio;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;


public class CompetenciaUniqueValidator implements ConstraintValidator<CompetenciaUnique, String> {
    @Autowired
    private CompetenciaServicio servicio;

    @Override
    public boolean isValid(String nombreCompetencia, ConstraintValidatorContext context) {
        return servicio.existeCompetencia(nombreCompetencia);
    }
    
    
}
