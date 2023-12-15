package com.uros.crud.repository;

import com.uros.crud.model.Student;
import com.uros.crud.specification.Filter;
import com.uros.crud.specification.QueryOperator;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.data.domain.Page;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;

@Component
public class CustomRepository {
    @Autowired
    StudentRepository studentRepository;

    public static Specification<Student> hasName(String title) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + title + "%");
    }

    public static Specification<Student> hasEmail(String name) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("email"), "%" + name + "%"));
    }

    public List<Student> findByNameAndEmail(String name, String email) {
        return studentRepository.findAll((where(hasEmail(email)).and(hasName(name))));
    }

    public Page<Student> getQueryResult(List<Filter> filters, Pageable pageable) {
        if (filters.size() > 0) {
            return studentRepository.findAll(getSpecificationFromFilters(filters), pageable);
        } else {
            return studentRepository.findAll(pageable);
        }
    }

    private Specification<Student> getSpecificationFromFilters(List<Filter> filter) {
        Specification<Student> specification = where(createSpecification(filter.remove(0)));
        for (Filter input : filter) {
            specification = specification.and(createSpecification(input));
        }
        return specification;
    }

    private Specification<Student> createSpecification(Filter input) {
        switch (input.getOperator()) {
            case EQUALS:

                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.equal(root.get(input.getField()),
                                castToRequiredType(root.get(input.getField()).getJavaType(), input.getValue()));

            case NOT_EQ:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.notEqual(root.get(input.getField()),
                                castToRequiredType(root.get(input.getField()).getJavaType(), input.getValue()));
            case GREATER_THAN:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.gt(root.get(input.getField()),
                                (Number) castToRequiredType(root.get(input.getField()).getJavaType(), input.getValue()));
            case LESS_THAN:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.lt(root.get(input.getField()),
                                (Number) castToRequiredType(root.get(input.getField()).getJavaType(), input.getValue()));
            case LIKE:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.like(root.get(input.getField()), "%" + input.getValue() + "%");
            case IN:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.in(root.get(input.getField()))
                                .value(castToRequiredType(root.get(input.getField()).getJavaType(), input.getValues()));
            default:
                throw new RuntimeException("Operation not supported yet");
        }
    }

    private Object castToRequiredType(Class fieldType, String value) {
        if (fieldType.isAssignableFrom(Double.class)) {
            return Double.valueOf(value);
        } else if (fieldType.isAssignableFrom(Integer.class)) {
            return Integer.valueOf(value);
        } else if (Enum.class.isAssignableFrom(fieldType)) {
            return Enum.valueOf(fieldType, value);
        }
        return value;
    }

    private Object castToRequiredType(Class fieldType, List<String> value) {
        List<Object> lists = new ArrayList<>();
        for (String s : value) {
            lists.add(castToRequiredType(fieldType, s));
        }
        return lists;
    }
}
