package com.example.demo.demo.Repository;

import com.example.demo.demo.Model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<Course, Long> {
}
