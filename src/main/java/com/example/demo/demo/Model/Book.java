package com.example.demo.demo.Model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity(name = "Book")
@Table(name = "book")
public class Book {
    @Id
    @SequenceGenerator(name = "Book_id_sequence" , sequenceName = "Book_id_sequence" , initialValue = 600)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "Book_id_sequence")
    private Long id;

    @Column(name = "book_name" , nullable = false )
    private String name ;

    @Column(name = "author_name")
    private String authorName ;

    @Column(name = "created_at" , nullable = false )
    private LocalDate createdAt ;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "student_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "student_id_fk"
            )
    )
    private Student student ;

    public void setStudent(Student student) {
        this.student = student;
    }



}
