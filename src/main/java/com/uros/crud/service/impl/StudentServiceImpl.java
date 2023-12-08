package com.uros.crud.service.impl;

import com.uros.crud.model.Student;
import com.uros.crud.model.StudentFilterCriteria;
import com.uros.crud.repository.StudentRepository;
import com.uros.crud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getStudents(int pageNumber, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        System.out.println(pageable);
        return studentRepository.findAll(pageable).get().toList();
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

    public List<Student> filterStudents(StudentFilterCriteria student) {
        return studentRepository.findStudentsByNameAndEmailAndGradeAndMinMaxGrade(student.getName(), student.getEmail(), student.getGrade(), student.getMinGrade(), student.getMaxGrade());
    }
}
