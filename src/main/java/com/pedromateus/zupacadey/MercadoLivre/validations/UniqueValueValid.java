package com.pedromateus.zupacadey.MercadoLivre.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UniqueValueValidate.class)
public @interface UniqueValueValid {
    String message() default "Há um valor como este já registrado na base de dados";
    Class<?>[] groups() default {};
    Class<? extends Payload> [] payload() default {};

    Class<?> domainValue();
    String field();

}
