package com.example.demo.demo.Controller;

import com.example.demo.demo.Model.*;
import com.example.demo.demo.Repository.StudentIdCardRepo;
import com.example.demo.demo.Repository.StudentRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController()
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentIdCardRepo studentIdCardRepo;

    // this method creates students only
    @GetMapping("/createStudents")
    public String doAllThings() {
        Faker faker = new Faker();
        for (int i = 0; i < 20; i++) {
            String fname = faker.name().firstName();
            String lname = faker.name().lastName();
            Integer age = faker.number().numberBetween(15, 55);
            String email = String.format("%s.%s@gmail.com", fname, lname);
            Student s = new Student(fname, lname, email, age);
            studentRepository.save(s);
        }
        return "Students were created";
    }

    @GetMapping("/getAllStudents")
    public List<Student> getAllStudents() {
        return studentRepository.findAll().stream().toList();
    }

    @GetMapping("/deleteAllStudent")
    public String deleteAllStudents() {
        studentRepository.deleteAll();
        if (studentRepository.findAll().isEmpty()) {
            return "All records deleted ";
        }
        return "Records not deleted ";
    }

    @GetMapping("/createIdCard")
    public String createStudentIdCard() {
        Faker faker = new Faker();
        List<Student> studentList = studentRepository.findAll();
        studentList.stream().forEach(student -> {
            StudentIdCard id = new StudentIdCard(faker.code().gtin13(), student);
            student.setStudentIdCard(id);
            studentRepository.save(student);
        });

        return studentIdCardRepo.findAll().toString();

    }

    @GetMapping("/getAllCards")
    public List<StudentIdCard> getAllIdCards() {
        return studentIdCardRepo.findAll().stream().toList();
    }

    @GetMapping("/deleteAllCards")
    public List<StudentIdCard> deleteAllIDCards() {
        studentIdCardRepo.deleteAll();
        return getAllIdCards();
    }

    @GetMapping("/test")
    public void testEnrolement () {
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

        student.addEnrolement(new Enrolement(
                new EnrolmentId(1L, 1L),
                student,
                new Course("Computer Science", "IT"),
                LocalDateTime.now()
        ));

        student.addEnrolement(new Enrolement(
                new EnrolmentId(1L, 2L),
                student,
                new Course("Amigoscode Spring Data JPA", "IT"),
                LocalDateTime.now().minusDays(18)
        ));

        student.addEnrolement(new Enrolement(
                new EnrolmentId(1L, 2L),
                student,
                new Course("Amigoscode Spring Data JPA", "IT"),
                LocalDateTime.now().minusDays(18)
        ));


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
    }

}
