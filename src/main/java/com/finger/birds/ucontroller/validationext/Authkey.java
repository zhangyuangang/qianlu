package com.finger.birds.ucontroller.validationext;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AuthkeyValidator.class)
public @interface Authkey {
	
	String message() default "authkey不正确";

    Class<?>[] groups() default {};  
     
    Class<? extends Payload>[] payload() default {};
}
