package com.uros.crud.dto;

import com.uros.crud.model.Student;
import lombok.Data;

@Data
public class StudentDto {
    private String name;
    private String email;
    private Integer grade;
}
