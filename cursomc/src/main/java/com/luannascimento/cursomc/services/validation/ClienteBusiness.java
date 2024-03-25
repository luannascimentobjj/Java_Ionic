package com.luannascimento.cursomc.services.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = ClienteBusinessValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ClienteBusiness {
	
	String message() default "Erro de validação";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
 
}
