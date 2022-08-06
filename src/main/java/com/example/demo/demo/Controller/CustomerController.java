package com.example.demo.demo.Controller;


import com.example.demo.demo.Model.Customer;
import com.example.demo.demo.Repository.CustomerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    private CustomerRepository repository;

    CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }

    @GetMapping("dashboard")
    public String welcome() {
        return "Welcome To Spring Boot ";
    }

    @GetMapping("/hello")
    public String hello () {
        return  "Hello a";
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return repository.findAll();

    }

    @GetMapping("/customer")
    public Customer saveCustomer(@RequestParam(value = "name", defaultValue = "sayed") String name,
                                 @RequestParam(value = "email", defaultValue = "sayed@gmail.com") String email) {
        Customer customer = new Customer(name, email);
        repository.save(customer);
        return customer;
    }

}
