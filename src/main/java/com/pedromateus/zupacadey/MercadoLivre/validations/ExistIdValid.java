package com.pedromateus.zupacadey.MercadoLivre.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistIdValidator.class)
@Target(ElementType.FIELD)
public @interface ExistIdValid {
    String message() default "Esse Id não consta na base de dados";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    Class<?> clazz();
}
