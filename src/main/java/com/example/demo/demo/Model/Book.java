package com.example.demo.demo.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity(name = "Book")
@Table(name = "book")
public class Book {

    @Id
    @Column(name = "book_id", updatable = false)
    @SequenceGenerator(name = "book_id_sequence", initialValue = 50, sequenceName = "book_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_id_sequence")
    private Long id;

    @Column(name = "book_name")
    private String bookName;
    @Column(name = "author_name")
    private String authorName;

    public Book(String bookName, String authorName) {
        this.bookName = bookName;
        this.authorName = authorName;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @ManyToOne()
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    @JsonIgnore
    private Student student;

    public Book() {

    }

    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", authorName='" + authorName + '\'' +
                ", student=" + student +
                '}';
    }

    public String getBookName() {
        return this.bookName ;
    }

    public String getAuthorName() {
        return this.authorName;
    }
}