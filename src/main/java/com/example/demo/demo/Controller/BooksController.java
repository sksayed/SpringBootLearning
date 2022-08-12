package com.example.demo.demo.Controller;


import com.example.demo.demo.Model.Book;
import com.example.demo.demo.Model.Student;
import com.example.demo.demo.Repository.BookRepository;
import com.example.demo.demo.Repository.StudentRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@RestController
public class BooksController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/createBooks")
    public String createBooks() {
        Faker faker = new Faker();
        int amount = 50 ;
        List<Book> bookList = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            String bookName = faker.name().fullName();
            String authorName = faker.book().author();
            long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
            long maxDay = LocalDate.of(2022, 12, 31).toEpochDay();
            long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
            LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
            Book b = Book.builder().createdAt(randomDate).authorName(authorName).name(bookName).build();
            bookRepository.save(b);
            bookList.add(b);
        }
        // one set of book has been created

        return "books added ";
    }

    @GetMapping("/getAllBooks")
    List<Book> getAllBooks () {
        return bookRepository.findAll().stream().toList();
    }
}
