package com.uros.crud.controller;

import com.uros.crud.dto.SearchDto;
import com.uros.crud.model.Student;
import com.uros.crud.service.FilterSpecification;
import com.uros.crud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private FilterSpecification filterSpecification;

    @GetMapping
    public ResponseEntity<Page<Student>> getStudents(Pageable pageable, @RequestParam String column, @RequestParam String value) {
        System.out.println(column);
        Specification<Student> spec = filterSpecification.getSearchSpecification(column, value);
        return new ResponseEntity<>(studentService.getStudents(pageable, spec), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return new ResponseEntity<>(studentService.getStudentById(id), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createStudent(@RequestBody Student newStudent) {
        studentService.addStudent(newStudent);
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
}
