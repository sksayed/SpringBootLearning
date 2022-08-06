package com.example.demo.demo.Repository;

import com.example.demo.demo.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s where s.email = ?1")
    Optional<Student> findStudentByEmail(String email);

    @Query("SELECT s FROM Student s where s.firstName like %:firstName% ")
    List<Student> findStudentByFirstNameContaining(@Param("firstName") String firstName);

    List<Student> findAll();

    @Transactional
    @Modifying
    @Query("DELETE FROM Student s where s.id = :id")
    int deleteStudentById(Long id);


}
