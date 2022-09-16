package com.example.demo.demo.Controller;

import com.example.demo.demo.Model.Customer;
import com.example.demo.demo.Model.StudentIdCard;
import com.example.demo.demo.Repository.CustomerRepository;
import com.example.demo.demo.Repository.StudentIdCardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studentIdCard")
public class StudentIdCardController {

    @Autowired
    private StudentIdCardRepo repository;

    StudentIdCardController(StudentIdCardRepo repository) {
        this.repository = repository;
    }

    //for getting all
    @GetMapping()
    public List<StudentIdCard> getAllStudentIdCards() {
        return repository.findAll();

    }

    //for getting
    @GetMapping("/{id}")
    public StudentIdCard getStudentIdCardById(@PathVariable(value = "id") Long customerID) {
        return this.repository.findById(customerID).orElseThrow(() -> new RuntimeException("studentIdCard not found "));
    }

    //for adding
    @PostMapping(consumes = {"application/json"})
    public StudentIdCard createStudentIdCard(@RequestBody StudentIdCard customer) {
        return this.repository.save(customer);
    }

    //for updating
    @PutMapping(value = "{id}", consumes = "application/json")
    public StudentIdCard updateStudentIdCard(@RequestBody StudentIdCard card, @PathVariable(value = "id") Long id) {
        StudentIdCard cs = this.repository.findById(id).orElseThrow(() -> {
            return new RuntimeException("StudentIdCard Not Found");
        });
        cs.setCardNumber(card.getCardNumber());
        return this.repository.save(cs);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<StudentIdCard> deleteStudentIdCard(@PathVariable(value = "id") Long customerId) {
        StudentIdCard cs = this.repository.findById(customerId).orElseThrow(() -> {
            return new RuntimeException("StudentIdCard Not Found");
        });
        if (cs != null) {
            this.repository.deleteById(customerId);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }


}