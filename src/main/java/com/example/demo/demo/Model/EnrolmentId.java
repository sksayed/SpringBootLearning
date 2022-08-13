package com.example.demo.demo.Model;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EnrolmentId implements Serializable {

    @Column(name ="course_id")
    private Long courseID;

    @Column(name = "student_id")
    private Long studentID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnrolmentId that = (EnrolmentId) o;
        return Objects.equals(courseID, that.courseID) && Objects.equals(studentID, that.studentID);
    }

    public EnrolmentId(Long courseID, Long studentID) {
        this.courseID = courseID;
        this.studentID = studentID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseID, studentID);
    }
}
