package com.example.SpringAss3APJ2.repo;

import com.example.SpringAss3APJ2.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankRepo extends JpaRepository<Card,Long> {
    //List<Card> findCardById(Long id);
}
