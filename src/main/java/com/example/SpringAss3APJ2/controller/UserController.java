package com.example.SpringAss3APJ2.controller;

import com.example.SpringAss3APJ2.repo.UserService;
import com.example.SpringAss3APJ2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController
    
{
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    PasswordEncoder passwordEncoder;
    @Autowired
    public void PasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping(value={"", "/", "home"})
    public String home()
    {
        return "index";
    }

    @GetMapping("/profile/{id}")
    public String profile(@PathVariable("id") Long id, Model model)
    {
        model.addAttribute("user", userService.findById(id));
        System.out.println(userService.findById(id));
        return "profile";
    }
    @GetMapping("/login")
    public String login()
    {
        return "login";
    }
    @GetMapping("/registration")
    public String registration()
    {
        return "registration";
    }
    @GetMapping("/user/changePassword/{id}")
    public String changePassword(@PathVariable("id") Long id, Model model)
    {
        model.addAttribute("user", userService.findById(id));
        System.out.println(userService.findById(id));
        return "changePass";
    }
    @PostMapping("/user/changePassword/")
    public String changePass(User user,@RequestParam("newPass") String password)
    {
        user.setPassword(passwordEncoder.encode(password));
        userService.save(user);
        return "profile";
    }
    @PostMapping("/registration")
    public String addUser(User user, @RequestParam("username") String email, Model model)
    {
        System.out.println("REGISTRATION:"+email);

       /* if (userDAO.findByUserName(email) != null)
        {
            model.addAttribute("message", "User exists!");
            return "registration";
        }
        else
        {
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDAO.addUser(user);
            return "redirect:/login";
        }*/
        return null;
    }
}