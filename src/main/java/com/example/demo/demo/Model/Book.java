package com.example.demo.demo.Model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "Book")
@Table(name = "book")
@Getter
@Setter
@NoArgsConstructor
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


    @ManyToOne()
    @JoinColumn(name = "student_id", referencedColumnName = "id" , nullable = false)
    //@JsonBackReference
    @JsonIgnoreProperties(value = { "books" ,"hibernateLazyInitializer", "handler" }, allowSetters = true)
    private Student student;

    public Book(String bookName, String authorName) {
        this.bookName = bookName;
        this.authorName = authorName;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", authorName='" + authorName + '\'' +
                '}';
    }
}