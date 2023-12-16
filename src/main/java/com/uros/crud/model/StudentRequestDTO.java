package com.uros.crud.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.logging.Logger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequestDTO {
    private static final Logger LOGGER = Logger.getLogger(Student.class.getName());
    @NotNull(message = "The item Name is required!")
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    @Email
    private String email;
    @NotNull
    @Min(value = 1, message = "must be more then 1 ")
    @Max(value = 5, message = "must be less then 5 ")
    private Integer grade;

    public void logStudentInstance() {
        LOGGER.info("Student instance details:");
        LOGGER.info("Name: " + name);
        LOGGER.info("Email: " + email);
        LOGGER.info("Grade: " + grade);
    }
}
