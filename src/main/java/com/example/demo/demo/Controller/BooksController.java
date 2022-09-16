package com.example.demo.demo.Controller;


import com.example.demo.demo.Model.Book;
import com.example.demo.demo.Model.Student;
import com.example.demo.demo.Model.StudentIdCard;
import com.example.demo.demo.Repository.BookRepository;
import com.example.demo.demo.Repository.StudentRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/book")
public class BooksController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/createBooks")
    public String createBooks() {
        return "Nothing Created ";
    }


    List<Book> getAllBooksTest() {
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

    @GetMapping("/getAllBooks")
    List<Book> getAll() {
        return bookRepository.findAll();
    }

    @PostMapping()
    ResponseEntity<Book> saveBook(@RequestBody Book book) {
        Book b = this.bookRepository.save(book);
        return ResponseEntity.ok(b);
    }

    @PutMapping("{id}")
    ResponseEntity<Book> updateBook(@PathVariable("id") Long id , @RequestBody Book book) {
        Book b = this.bookRepository.save(book);
        return ResponseEntity.ok(b);
    }

    @DeleteMapping("/id")
    ResponseEntity<Book> deleteBook(@PathVariable("id") Long id, @RequestBody Book book) {
        Book existingBook = this.bookRepository.findById(id).orElse(null);
        if (existingBook == null) {
            return ResponseEntity.notFound().build();
        }
        this.bookRepository.deleteById(id);
        return ResponseEntity.ok(existingBook);
    }

    @GetMapping("/id")
    ResponseEntity<Book> getBookById(@PathVariable("id") Long id, @RequestBody Book book) {
        Book existingBook = this.bookRepository.findById(id).orElse(null);
        if (existingBook == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(existingBook);
    }
}
