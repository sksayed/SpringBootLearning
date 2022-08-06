package com.example.demo.demo.Repository;

import com.example.demo.demo.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
