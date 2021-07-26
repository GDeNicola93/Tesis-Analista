package com.sedback.SEDBack.Validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = AreaUniqueValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface AreaUnique {
    String message() default "Ya existe un área registrada con ese nombre.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
