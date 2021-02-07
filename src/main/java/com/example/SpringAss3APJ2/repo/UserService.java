package com.example.SpringAss3APJ2.repo;

import com.example.SpringAss3APJ2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User findById(Long id){
        return userRepo.findById(id).orElse(null);
    }

    public User save(User user){
        return userRepo.save(user);
    }
}
