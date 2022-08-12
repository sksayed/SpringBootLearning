package com.example.demo.demo.Repository;


import com.example.demo.demo.Model.StudentIdCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentIdCardRepo extends JpaRepository<StudentIdCard , Long> {
}
