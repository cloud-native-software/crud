package com.uros.crud.validation.student;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = StudentFilterValidator.class)
public @interface StudentFilterValid {
    String message() default "minGrade must be smaller than or equal to maxGrade";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}