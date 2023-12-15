package com.uros.crud.mapper;

import com.uros.crud.model.StudentFilter;
import com.uros.crud.specification.Filter;
import com.uros.crud.specification.QueryOperator;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentFilterMapper {

    @Mappings({
            @Mapping(target = "field", constant = "name"),
            @Mapping(target = "operator", constant = "EQUALS"),
            @Mapping(target = "value", source = "name")
    })
    Filter mapNameToFilter(String name);

    @Mappings({
            @Mapping(target = "field", constant = "email"),
            @Mapping(target = "operator", constant = "EQUALS"),
            @Mapping(target = "value", source = "email")
    })
    Filter mapEmailToFilter(String email);

    @Mappings({
            @Mapping(target = "field", constant = "grade"),
            @Mapping(target = "operator", constant = "EQUALS"),
            @Mapping(target = "value", source = "grade")
    })
    Filter mapGradeToFilter(Integer grade);

    @Mappings({
            @Mapping(target = "field", constant = "grade"),
            @Mapping(target = "operator", constant = "LESS_THAN"),
            @Mapping(target = "value", source = "maxGrade")
    })
    Filter mapMaxGradeToFilter(Integer maxGrade);
    @Mappings({
            @Mapping(target = "field", constant = "grade"),
            @Mapping(target = "operator", constant = "GREATER_THAN"),
            @Mapping(target = "value", source = "minGrade")
    })
    Filter mapMinGradeToFilter(Integer minGrade);

    default List<Filter> mapStudentFilterToFilters(StudentFilter studentFilter) {
        List<Filter> filters = new ArrayList<>();

        if (studentFilter.getName() != null) {
            filters.add(mapNameToFilter(studentFilter.getName()));
        }
        if (studentFilter.getEmail() != null) {
            filters.add(mapEmailToFilter(studentFilter.getEmail()));
        }
        if (studentFilter.getGrade() != null) {
            filters.add(mapGradeToFilter(studentFilter.getGrade()));
        }
        if (studentFilter.getMaxGrade() != null) {
            filters.add(mapMaxGradeToFilter(studentFilter.getMaxGrade()));
        }
        if (studentFilter.getMinGrade() != null) {
            filters.add(mapMinGradeToFilter(studentFilter.getMinGrade()));
        }
        // Add other conditions for mapping properties

        return filters;
    }
}


