package com.uros.crud.mapper;

import com.uros.crud.model.Student;
import com.uros.crud.model.StudentRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    @Mapping(target = "id", ignore = true) // As id is auto-generated
    Student studentRequestDTOToStudent(StudentRequestDTO studentRequestDTO);
}
