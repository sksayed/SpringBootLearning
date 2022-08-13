package com.example.demo.demo.Repository;

import com.example.demo.demo.Model.Enrolement;
import com.example.demo.demo.Model.EnrolmentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrolementRepo extends JpaRepository<Enrolement , EnrolmentId> {
}
