package com.example.demo.demo.Repository;

import com.example.demo.demo.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book , Long> {
}
