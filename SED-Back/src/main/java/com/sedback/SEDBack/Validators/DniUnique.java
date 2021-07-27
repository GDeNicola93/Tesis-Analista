package com.sedback.SEDBack.Validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = DniUniqueValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DniUnique {
    String message() default "El dni ingresado ya pertenece a otro empleado.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
