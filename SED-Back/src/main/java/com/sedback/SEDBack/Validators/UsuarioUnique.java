package com.sedback.SEDBack.Validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = UsuarioUniqueValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UsuarioUnique {
    String message() default "El nombre de usuario ingresado ya se encuentra asignado a otro usuario.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
