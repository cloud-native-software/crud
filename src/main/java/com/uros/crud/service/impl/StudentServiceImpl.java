package com.uros.crud.service.impl;

import com.uros.crud.mapper.StudentFilterMapper;
import com.uros.crud.mapper.StudentMapper;
import com.uros.crud.model.Student;
import com.uros.crud.model.StudentFilter;
import com.uros.crud.model.StudentRequestDTO;
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

    private final StudentRepository studentRepository;
    private final CustomRepository customRepository;
    private final StudentFilterMapper studentFilterMapper;
    private final StudentMapper studentMapper;

    @Autowired
    public StudentServiceImpl(
            StudentRepository studentRepository,
            CustomRepository customRepository,
            StudentFilterMapper studentFilterMapper,
            StudentMapper studentMapper
    ) {
        this.studentRepository = studentRepository;
        this.customRepository = customRepository;
        this.studentFilterMapper = studentFilterMapper;
        this.studentMapper=studentMapper;
    }

    @Override
    public Page<Student> getStudents(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(RuntimeException::new);
    }


    public Student addStudent(StudentRequestDTO newStudent) {
       Student student= studentRepository.save(studentMapper.studentRequestDTOToStudent(newStudent));
       return  student;
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

    public Page<Student> filterStudents(StudentFilter student, Pageable pageable) {
        return studentRepository.findStudentsByFilterCriteria(student, pageable);
    }

    @Override
    public Page<Student> findDyn(StudentFilter studentFilter,Pageable pageable){
        List<Filter> filters= studentFilterMapper.mapStudentFilterToFilters(studentFilter);
       return customRepository.getQueryResult(filters,pageable);
    }
}
