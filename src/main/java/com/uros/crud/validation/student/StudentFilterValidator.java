package com.uros.crud.validation.student;

import com.uros.crud.model.StudentFilter;
import com.uros.crud.validation.student.StudentFilterValid;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class StudentFilterValidator implements ConstraintValidator<StudentFilterValid, StudentFilter> {
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
    );

    public boolean isValid(StudentFilter studentFilter, ConstraintValidatorContext context) {
        boolean minMax = true;
        boolean email = true;
        if (studentFilter == null) {
            return false;
        }
        if (studentFilter.getMaxGrade() != null || studentFilter.getMinGrade() != null) {
            minMax = studentFilter.getMaxGrade() >= studentFilter.getMinGrade();

            if (!minMax) {
                context.buildConstraintViolationWithTemplate("minGrade must be smaller than or equal to maxGrade")
                        .addPropertyNode("minGrade") // Specify the field associated with the error
                        .addConstraintViolation();
            }
        }
        if (studentFilter.getEmail() != null) {
            email = EMAIL_PATTERN.matcher(studentFilter.getEmail()).matches();
            if (!email) {
                context.buildConstraintViolationWithTemplate("email must good format")
                        .addPropertyNode("email")
                        .addConstraintViolation();
            }
        }
        return email && minMax;
    }
}
