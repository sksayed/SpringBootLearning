package com.example.demo.demo.Controller;


import com.example.demo.demo.Model.Customer;
import com.example.demo.demo.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.Callable;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository repository;

    CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }


    @GetMapping()
    public List<Customer> getAllCustomers() {
        return repository.findAll();

    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable(value = "id") Long customerID) {
        return this.repository.findById(customerID).orElseThrow(() -> new RuntimeException("User not found "));
    }

    @PostMapping(consumes = {"application/json"})
    public Customer createCustomer(@RequestBody Customer customer) {
        return this.repository.save(customer);
    }

    @PutMapping(value = "{id}", consumes = "application/json")
    public Customer updateCustomer(@RequestBody Customer customer, @PathVariable(value = "id") Long id) {
        Customer cs = this.repository.findById(id).orElseThrow(() -> {
            return new RuntimeException("Customer Not Found");
        });
        cs.setEmailAddress(customer.getEmailAddress());
        cs.setFirstName(customer.getFirstName());
        return this.repository.save(cs);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable(value = "id") Long customerId) {
        Customer cs = this.repository.findById(customerId).orElseThrow(() -> {
            return new RuntimeException("Customer Not Found");
        });
        if (cs != null) {
            this.repository.deleteById(customerId);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }


}
