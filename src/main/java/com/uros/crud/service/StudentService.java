package com.uros.crud.service;

import com.uros.crud.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> getStudents(int pageNumber, int pageSize, String sortBy);
    Student getStudentById(Long id);
    void addStudent(Student newStudent);
    void deleteStudent(Long id);
    void updateStudent(Long id, Student updatedStudent);
}
