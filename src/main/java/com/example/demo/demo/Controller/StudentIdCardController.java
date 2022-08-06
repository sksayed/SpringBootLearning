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

@RestController
public class StudentIdCardController {

    @Autowired
    private StudentRepository studentRepository ;
    @Autowired
    private StudentIdCardRepo studentIdCardRepo;

    @GetMapping("/createIdCard")
    String createStudentIDCard () {
        List<Student> students = studentRepository.findAll();
        if (students.isEmpty()) {
            return  "Create Students First";
        }
        Faker faker = new Faker();
        for (int i =0 ; i< students.size() ; i++) {
            String seqNumber = faker.company().buzzword();
            StudentIdCard studentIdCard = new StudentIdCard(seqNumber , students.get(i));
            studentIdCardRepo.save(studentIdCard);
        }
        return "Id card created ";
    }

    @GetMapping("/getStudentsFromID")
    List<Student> getStudentsFromId () {
        List<Student> studentList = new ArrayList<Student>();
        studentIdCardRepo.findAll().forEach(studentIdCard -> studentList.add(studentIdCard.getStudent()));
        return  studentList ;
    }
}
