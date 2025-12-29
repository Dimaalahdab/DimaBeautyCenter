package com.example.dimaBeauty.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.dimaBeauty.Entity.User;
import com.example.dimaBeauty.Repository.UserRepository;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    UserRepository userRepo;

    @PostMapping("/login")
    public String login(@RequestBody User u) {

        User dbUser = userRepo.findByUsername(u.getUsername());

        if (dbUser == null)
            return "USER_NOT_FOUND";

        if (!dbUser.getPassword().equals(u.getPassword()))
            return "WRONG_PASSWORD";

        return "LOGIN_SUCCESS";
    }
}
