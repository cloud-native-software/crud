package com.uros.crud.service;

import com.uros.crud.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.uros.crud.model.StudentDTO;
import com.uros.crud.specification.Filter;


import java.util.List;

public interface StudentService {
    Page<Student> getStudents(Pageable pageable);
    Student getStudentById(Long id);
    void addStudent(Student newStudent);
    void deleteStudent(Long id);
    void updateStudent(Long id, Student updatedStudent);
    List<Student>filterStudents(StudentDTO student, Pageable pageable);
    public List<Student> findDyn(List<Filter> filter,Pageable pageable);
}
