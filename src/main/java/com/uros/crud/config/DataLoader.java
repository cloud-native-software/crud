package com.uros.crud.config;

import com.uros.crud.model.Student;
import com.uros.crud.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    public StudentRepository studentRepository;

    @Override
    public void run(String... args) throws Exception {
            Student mariam = new Student(
                    1L,
                    "Mariam",
                    "mariam@gmail.com",
                    7
            );
            Student alex = new Student(
                    2L,
                    "Alex",
                    "alex@gmail.com",
                    8
            );

            studentRepository.saveAll(List.of(mariam, alex));
    }
}
