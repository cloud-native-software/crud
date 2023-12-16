package com.uros.crud.model;


import com.uros.crud.validation.student.StudentFilterValid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.logging.Logger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@StudentFilterValid
public class StudentFilter {
    private static final Logger LOGGER = Logger.getLogger(Student.class.getName());
    private String name;
    private String email;
    private Integer grade;
    private Integer minGrade;//<
    private Integer maxGrade;//>

    public void logStudentInstance() {
        LOGGER.info("Student instance details:");
        LOGGER.info("Name: " + name);
        LOGGER.info("Email: " + email);
        LOGGER.info("Grade: " + grade);
    }
}

