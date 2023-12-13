package com.uros.crud.service.impl;

import com.uros.crud.model.Student;
import com.uros.crud.repository.StudentRepository;
import com.uros.crud.service.StudentService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Page<Student> getStudents(Pageable pageable, Specification<Student> specification) {

        return studentRepository.findAll(specification, pageable);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public void addStudent(Student newStudent) {
        studentRepository.save(newStudent);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public void updateStudent(Long id, Student updatedStudent) {
        Student student = studentRepository.findById(id).orElseThrow(RuntimeException::new);
        student.setName(updatedStudent.getName());
        student.setEmail(updatedStudent.getEmail());
        studentRepository.save(student);
    }

}
