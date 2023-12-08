package com.uros.crud.repository;

import com.uros.crud.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    public List<Student> findByNameAndEmail(String name, String email);

    public List<Student> findByName(String name);

    public List<Student> findByEmail(String email);
    @Query("SELECT s FROM Student s WHERE " +
            "(:name is null or s.name = :name) " +
            "and (:email is null or s.email = :email) " +
            "and (:grade is null or s.grade = :grade) " +
            "and (:minGrade is null or s.grade >= :minGrade) " +
            "and (:maxGrade is null or s.grade <= :maxGrade)")
    List<Student> findStudentsByNameAndEmailAndGradeAndMinMaxGrade(
            @Param("name") String name,
            @Param("email") String email,
            @Param("grade") Integer grade,
            @Param("minGrade") Integer minGrade,
            @Param("maxGrade") Integer maxGrade
    );
}
