package com.uros.crud.model;


import com.uros.crud.specification.Filter;
import com.uros.crud.specification.QueryOperator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
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
    public List<Filter> mapToFilters() {
        List<Filter> filters = new ArrayList<>();

        if (name != null) {
            filters.add(Filter.builder()
                    .field("name")
                    .operator(QueryOperator.EQUALS)
                    .value(name)
                    .build());
        }

        if (email != null) {
            filters.add(Filter.builder()
                    .field("email")
                    .operator(QueryOperator.EQUALS)
                    .value(email)
                    .build());
        }

        if (grade != null) {
            filters.add(Filter.builder()
                    .field("grade")
                    .operator(QueryOperator.EQUALS)
                    .value(String.valueOf(grade))
                    .build());
        }
        if ( minGrade != null) {
            filters.add(Filter.builder()
                    .field("grade")
                    .operator(QueryOperator.GREATER_THAN)
                    .value(String.valueOf(minGrade))
                    .build());
        }
        if ( maxGrade != null) {
            filters.add(Filter.builder()
                    .field("grade")
                    .operator(QueryOperator.LESS_THAN)
                    .value(String.valueOf(maxGrade))
                    .build());
        }

        return filters;
    }
}

