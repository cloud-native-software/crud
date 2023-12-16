package com.uros.crud.validation.studentDto;

import com.uros.crud.model.StudentRequestDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;


public class StudentRequestDTOValidator implements ConstraintValidator<StudentRequestDTOValid, StudentRequestDTO> {
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
    );

    @Override
    public boolean isValid(StudentRequestDTO studentRequestDTO, ConstraintValidatorContext context) {
        if (studentRequestDTO == null) {
            return false;
        }
        boolean studentDto = true;

        if (!EMAIL_PATTERN.matcher(studentRequestDTO.getEmail()).matches()) {
            context.buildConstraintViolationWithTemplate("email must good format")
                    .addPropertyNode("email")
                    .addConstraintViolation();
            studentDto = false;
        }

        if (studentRequestDTO.getGrade() < 1 || studentRequestDTO.getGrade() > 5) {
            context.buildConstraintViolationWithTemplate("you need to send grade between 1 and 5")
                    .addPropertyNode("grade") // Specify the field associated with the error
                    .addConstraintViolation();
            studentDto = false;
        }

        return studentDto;
    }
}
