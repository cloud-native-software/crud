package com.uros.crud.validation.studentDto;

import com.uros.crud.validation.student.StudentFilterValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = StudentRequestDTOValidator.class)
public @interface StudentRequestDTOValid {
    String message() default "provide right Student's properties";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
