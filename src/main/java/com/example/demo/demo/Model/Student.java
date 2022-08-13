package com.example.demo.demo.Model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Student")
@Table(name = "student")
@DynamicInsert
@DynamicUpdate
public class Student {

    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "student_sequence"
    )
    @Column(
            name = "id"
    )
    private Long id;

    @Column(
            name = "first_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String firstName;

    @Column(
            name = "last_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String lastName;

    @Column(
            name = "email",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String email;

    @Column(
            name = "age",
            nullable = false

    )
    private Integer age;

    @OneToOne(
            mappedBy = "student",
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private StudentIdCard studentIdCard;

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true,
            fetch = FetchType.LAZY ,
            mappedBy = "student"
    )
    private List<Book> bookList = new ArrayList<>();

    public Student(String firstName,
                   String lastName,
                   String email,
                   Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    public Student() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }

    public StudentIdCard getStudentIdCard() {
        return studentIdCard;
    }

    public void setStudentIdCard(StudentIdCard studentIdCard) {
        this.studentIdCard = studentIdCard;
    }

    public void addBook(Book book) {
        if (book != null) {
            this.bookList.add(book);
            book.setStudent(this);
        }
    }

    public void removeBook(Book book) {
        if (book != null) {
            if (this.bookList.contains(book)) {
                bookList.remove(book);
                book.setStudent(null);
            }
        }
    }

    public List<Book> getBooks() {
        return this.bookList ;
    }

    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST , CascadeType.REMOVE},
            mappedBy ="student"
    )
    private List<Enrolement> enrolementList = new ArrayList<>();

    public void addEnrolement (Enrolement enrolement) {
        if (enrolement != null) {
            if(!this.enrolementList.contains(enrolement)){
                this.enrolementList.add(enrolement);
                enrolement.addStudent(this);
            }
        }
    }

    public void removeEnrolement (Enrolement enrolement) {
        if (enrolement != null) {
            if(this.enrolementList.contains(enrolement)){
                this.enrolementList.remove(enrolement);
                enrolement.addStudent(null);
            }
        }
    }
}
