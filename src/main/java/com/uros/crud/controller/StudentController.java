package com.uros.crud.controller;

import com.uros.crud.model.Student;
import com.uros.crud.model.StudentFilterCriteria;
import com.uros.crud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> getStudents(@RequestParam int pageNumber, @RequestParam int pageSize, @RequestParam String sortBy) {
        return new ResponseEntity<>(studentService.getStudents(pageNumber, pageSize, sortBy), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return new ResponseEntity<>(studentService.getStudentById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student newStudent) {
        studentService.addStudent(newStudent);
        return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
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
    @GetMapping("filtred")
    public ResponseEntity<List<Student>>  filter(@ModelAttribute StudentFilterCriteria studentFilterCriteria){
        return new ResponseEntity<>(studentService.filterStudents(studentFilterCriteria),HttpStatus.OK);
    }
}
