package com.example.demo.demo.Controller;

import com.example.demo.demo.Model.Student;
import com.example.demo.demo.Model.StudentIdCard;
import com.example.demo.demo.Repository.StudentIdCardRepo;
import com.example.demo.demo.Repository.StudentRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
            studentIdCardRepo.save(id);
        });

        return studentIdCardRepo.findAll().toString();

    }

    @GetMapping("/getAllCards")
    public List<StudentIdCard> getAllIdCards () {
        return studentIdCardRepo.findAll().stream().toList();
    }

}
