package com.example.demo.demo.Controller;


import com.example.demo.demo.Model.Book;
import com.example.demo.demo.Model.Student;
import com.example.demo.demo.Model.StudentIdCard;
import com.example.demo.demo.Repository.BookRepository;
import com.example.demo.demo.Repository.StudentRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class BooksController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/createBooks")
    public String createBooks() {
        return "Nothing Created ";
    }


    @GetMapping("/getAllBooks")
    List<Book> getAllBooks() {
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = String.format("%s.%s@amigoscode.edu", firstName, lastName);
        Student student = new Student(
                firstName,
                lastName,
                email,
                faker.number().numberBetween(17, 55));

        student.addBook(
                new Book("Clean Code", "sayed"));


        student.addBook(
                new Book("Think and Grow Rich", "sayed"));

        student.addBook(
                new Book("Spring Data JPA", "sayed"));

        StudentIdCard studentIdCard = new StudentIdCard(
                "123456789",
                student);

        student.setStudentIdCard(studentIdCard);

        studentRepository.save(student);

        studentRepository.findById(1L)
                .ifPresent(s -> {
                    System.out.println("fetch book lazy...");
                    List<Book> books = student.getBooks();
                    books.forEach(book -> {
                        System.out.println(
                                s.getFirstName() + " borrowed " + book.getBookName());
                    });
                });

        return bookRepository.findAll().stream().toList();
    }
}
