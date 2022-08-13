package com.example.demo.demo.Model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Course")
@Table(name = "course")
public class Course {

    @Id
    @SequenceGenerator(name = "course_id_generator", sequenceName = "course_id_generator", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "course_id_generator", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "department")
    private String department;

    public Course(String courseName, String department) {
        this.courseName = courseName;
        this.department = department;
    }

    public Course() {

    }

    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST , CascadeType.REMOVE},
            mappedBy ="course"
    )
    private List<Enrolement> enrolementList = new ArrayList<>();

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Enrolement> getEnrolementList() {
        return enrolementList;
    }

    public void addEnrolement (Enrolement enrolement) {
        if (enrolement != null) {
            if(!this.enrolementList.contains(enrolement)){
                this.enrolementList.add(enrolement);
                enrolement.addCourse(this);
            }
        }
    }

    public void removeEnrolement (Enrolement enrolement) {
        if (enrolement != null) {
            if(this.enrolementList.contains(enrolement)){
                this.enrolementList.remove(enrolement);
                enrolement.setCourse(null);
            }
        }
    }

}
