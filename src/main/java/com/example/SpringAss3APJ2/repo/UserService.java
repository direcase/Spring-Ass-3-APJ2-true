package com.example.SpringAss3APJ2.repo;

import com.example.SpringAss3APJ2.model.Authority;
import com.example.SpringAss3APJ2.model.Role;
import com.example.SpringAss3APJ2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    public User findByUserName(String u){
        return  userRepo.findUserByEmail(u);
    }



}
