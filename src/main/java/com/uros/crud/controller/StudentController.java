package com.uros.crud.controller;

import com.uros.crud.mapper.StudentFilterMapper;
import com.uros.crud.model.Student;
import com.uros.crud.model.StudentFilter;
import com.uros.crud.model.StudentRequestDTO;
import com.uros.crud.service.StudentService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@Validated
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<Page<Student>> getStudents(Pageable pageable) {
        return new ResponseEntity<>(studentService.getStudents(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@Positive @PathVariable Long id) {
        return new ResponseEntity<>(studentService.getStudentById(id), HttpStatus.OK);
    }

   @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody @Valid StudentRequestDTO newStudentDTO) {
       Student student= studentService.addStudent(newStudentDTO);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStudent(@PathVariable Long id, @RequestBody Student student) {
        studentService.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    @GetMapping("filterJPQL")
    public ResponseEntity <Page<Student>> filter(@ModelAttribute StudentFilter studentFilterCriteria, Pageable pageable) {
        return new ResponseEntity<>(studentService.filterStudents(studentFilterCriteria, pageable), HttpStatus.OK);
    }

    @GetMapping("filterDynamic")
    public ResponseEntity<Page<Student>> filterDynamic(@ModelAttribute StudentFilter studentFilter, Pageable pageable) {
        return new ResponseEntity<>(studentService.findDyn(studentFilter, pageable), HttpStatus.OK);
    }
}
