package com.example.demo.demo.Controller;

import com.example.demo.demo.Model.*;
import com.example.demo.demo.Repository.StudentIdCardRepo;
import com.example.demo.demo.Repository.StudentRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository repository;

    @Autowired
    private StudentIdCardRepo studentIdCardRepo;

    // this method creates students only
    @GetMapping("/createStudents/{number}")
    public ResponseEntity<List<Student>> doAllThings(@PathVariable(value = "number", required = false) Optional<Integer> number) {
        Faker faker = new Faker();
        int amount = number.orElse(20);
        for (int i = 0; i < amount; i++) {
            String fname = faker.name().firstName();
            String lname = faker.name().lastName();
            Integer age = faker.number().numberBetween(15, 55);
            String email = String.format("%s.%s@gmail.com", fname, lname);
            Student s = new Student(fname, lname, email, age);
            repository.save(s);
        }
        return ResponseEntity.ok(repository.findAll().stream().toList());
    }

    @GetMapping("/getAllStudents")
    public List<Student> getAllStudents() {
        return repository.findAll().stream().toList();
    }

    @GetMapping("/deleteAllStudent")
    public String deleteAllStudents() {
        repository.deleteAll();
        if (repository.findAll().isEmpty()) {
            return "All records deleted ";
        }
        return "Records not deleted ";
    }


    @GetMapping("/enrolementList/{studentId}")
    public List<Enrolement> enrolementListOfaStudent(@PathVariable("studentId") Long id) {
        return repository.findById(id).get().getEnrolementList();
    }

    @GetMapping("/test/enrolement")
    public String testEnrolement() {
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
                String.valueOf(faker.number().randomNumber()),
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



        repository.save(student);
        StringBuilder sb = new StringBuilder();
        repository.findById(1L)
                .ifPresent(s -> {
                    sb.append("fetch book lazy...");
                    System.out.println("fetch book lazy...");
                    List<Book> books = student.getBooks();
                    books.forEach(book -> {
                        sb.append(s.getFirstName() + " borrowed " + book.getBookName());
                        System.out.println(
                                s.getFirstName() + " borrowed " + book.getBookName());
                    });
                });
        return sb.toString();
    }


    @GetMapping()
    public List<Student> getAllCustomers() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Student getCustomerById(@PathVariable(value = "id") Long customerID) {
        return this.repository.findById(customerID).orElseThrow(() -> new RuntimeException("User not found "));
    }

    @PostMapping(consumes = {"application/json"})
    public Student createCustomer(@RequestBody Student customer) {
        return this.repository.save(customer);
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public Student updateCustomer(@RequestBody Student customer, @PathVariable(value = "id") Long id) {
        Student cs = this.repository.findById(id).orElseThrow(() -> {
            return new RuntimeException("Student Not Found");
        });

        return this.repository.save(customer);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> deleteCustomer(@PathVariable(value = "id") Long customerId) {
        Student cs = this.repository.findById(customerId).orElseThrow(() -> {
            return new RuntimeException("Student Not Found");
        });
        if (cs != null) {
            this.repository.deleteById(customerId);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
