package com.example.SpringAss3APJ2.repo;

import com.example.SpringAss3APJ2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User,Long> {
List<User> findUserById(Long id);
}
