package com.uros.crud.service.impl;

import com.uros.crud.model.Student;
import com.uros.crud.model.StudentDTO;
import com.uros.crud.repository.CustomRepository;
import com.uros.crud.repository.StudentRepository;
import com.uros.crud.service.StudentService;
import com.uros.crud.specification.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CustomRepository customRepository;

    @Override
    public Page<Student> getStudents(Pageable pageable) {
        return studentRepository.findAll(pageable);
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

    public List<Student> filterStudents(StudentDTO student, Pageable pageable) {
        return studentRepository.findStudentsByFilterCriteria(student, pageable);
    }

    @Override
    public List<Student> findDyn(List<Filter> filter,Pageable pageable){
       return customRepository.getQueryResult(filter,pageable);
    }
}
