package com.example.demo.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Student")
@Table(name = "student")
@Getter
@Setter
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
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE},
            orphanRemoval = true,
            fetch = FetchType.LAZY,
            mappedBy = "student"
    )
    //@JsonManagedReference
    @JsonIgnoreProperties(value = { "student" ,"hibernateLazyInitializer", "handler" }, allowSetters = true)
    private List<Book> books = new ArrayList<>();

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


    public void addBook(Book book) {
        if (book != null) {
            this.books.add(book);
            book.setStudent(this);
        }
    }

    public void removeBook(Book book) {
        if (book != null) {
            if (this.books.contains(book)) {
                books.remove(book);
                book.setStudent(null);
            }
        }
    }

    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "student"
    )
    private List<Enrolement> enrolementList = new ArrayList<>();

    public void addEnrolement(Enrolement enrolement) {
        if (enrolement != null) {
            if (!this.enrolementList.contains(enrolement)) {
                this.enrolementList.add(enrolement);
                enrolement.addStudent(this);
            }
        }
    }

    public void removeEnrolement(Enrolement enrolement) {
        if (enrolement != null) {
            if (this.enrolementList.contains(enrolement)) {
                this.enrolementList.remove(enrolement);
                enrolement.addStudent(null);
            }
        }
    }

    public List<Book> getBooks() {
        return this.books;
    }
}
