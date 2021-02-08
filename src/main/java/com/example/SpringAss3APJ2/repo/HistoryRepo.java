package com.example.SpringAss3APJ2.repo;

import com.example.SpringAss3APJ2.model.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryRepo extends JpaRepository<History, String> {
    //List<History> findAllByCard_id(String id);
    // List<History> findHistoriesByCard_id(int id);

    //List<History> findAllByCard_id(Long id);
}
