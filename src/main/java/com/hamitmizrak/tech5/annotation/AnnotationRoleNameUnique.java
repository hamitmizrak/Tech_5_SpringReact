package com.hamitmizrak.tech5.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Role Name have to unique
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {RoleNameUniqueValidation.class})
public @interface AnnotationRoleNameUnique {

    String message() default "{role.name.unique.constraints.NotNull.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
} //end class