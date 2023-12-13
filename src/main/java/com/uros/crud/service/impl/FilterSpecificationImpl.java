package com.uros.crud.service.impl;

import com.uros.crud.model.Student;
import com.uros.crud.service.FilterSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class FilterSpecificationImpl implements FilterSpecification {
    public Specification<Student> getSearchSpecification(String column, String value) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(column), value);
    }
}
