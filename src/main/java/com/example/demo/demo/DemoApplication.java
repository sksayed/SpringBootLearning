package com.example.demo.demo;

import com.example.demo.demo.Model.Customer;
import com.example.demo.demo.Repository.CustomerRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableJpaRepositories
public class DemoApplication {


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
        return args -> {

           /* Faker faker = new Faker();
            for (int i = 0; i < 20; i++) {
                String fname = faker.name().fullName();
                String email = String.format("%s.%s@gmail.com", faker.name().firstName(), faker.name().lastName());
                Customer customer = new Customer(email, fname);
                customerRepository.save(customer);
            }*/

        };

    }
}
