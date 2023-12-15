package com.uros.crud.service;

import com.uros.crud.model.Student;
import com.uros.crud.model.StudentRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.uros.crud.model.StudentFilter;
import com.uros.crud.specification.Filter;


import java.util.List;

public interface StudentService {
    Page<Student> getStudents(Pageable pageable);

    Student getStudentById(Long id);

    Student addStudent(StudentRequestDTO newStudent);

    void deleteStudent(Long id);

    void updateStudent(Long id, Student updatedStudent);

    Page<Student> filterStudents(StudentFilter student, Pageable pageable);

    public Page<Student> findDyn(StudentFilter studentFilter, Pageable pageable);
}
