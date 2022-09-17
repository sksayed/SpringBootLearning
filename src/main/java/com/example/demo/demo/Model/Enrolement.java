package com.example.demo.demo.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
@Getter
@Setter
@Entity(name = "EnrolementEntity")
@Table(name = "enrolement")
public class Enrolement {

    @EmbeddedId
    EnrolmentId id;

    @ManyToOne()
    @MapsId("studentID")
    @JoinColumn(name = "student_id", foreignKey = @ForeignKey(name = "std_fk_enrlmnt"))
    @JsonIgnore
    private Student student;

    @ManyToOne
    @MapsId("courseID")
    @JoinColumn(name = "course_id", foreignKey = @ForeignKey(name = "course_fk_enrlmnt"))
    @JsonIgnore
    private Course course;


    LocalDateTime dateTime;

    public void addCourse(Course course) {
        this.course = course;
    }

    public void addStudent(Student student) {
        this.student = student;
    }

    public Enrolement(EnrolmentId id, Student student, Course course, LocalDateTime dateTime) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.dateTime = dateTime;
    }

    public Enrolement() {

    }
}
