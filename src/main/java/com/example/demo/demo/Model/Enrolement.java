package com.example.demo.demo.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    public EnrolmentId getId() {
        return id;
    }

    public void setId(EnrolmentId id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
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
