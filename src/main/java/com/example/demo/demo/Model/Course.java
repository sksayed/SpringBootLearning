package com.example.demo.demo.Model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "Course")
@Table(name = "course")
@NoArgsConstructor
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

    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST , CascadeType.REMOVE},
            mappedBy ="course"
    )
    private List<Enrolement> enrolementList = new ArrayList<>();



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
