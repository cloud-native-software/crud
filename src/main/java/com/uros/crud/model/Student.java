package com.uros.crud.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.logging.Logger;
import lombok.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student")
public class Student {
    private static final Logger LOGGER = Logger.getLogger(Student.class.getName());
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private Integer grade;

    public void logStudentInstance() {
        LOGGER.info("Student instance details:");
        LOGGER.info("Name: " + name);
        LOGGER.info("Email: " + email);
        LOGGER.info("Grade: " + grade);
    }
}
