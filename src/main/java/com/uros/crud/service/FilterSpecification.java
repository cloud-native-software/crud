package com.uros.crud.service;

import com.uros.crud.dto.SearchDto;
import com.uros.crud.model.Student;
import org.springframework.data.jpa.domain.Specification;

public interface FilterSpecification {
    Specification<Student> getSearchSpecification(String column, String value);
}
