package com.example.hibernateexample.web.rest;

import com.example.hibernateexample.entity.User;
import com.example.hibernateexample.exception.BadCredentials;
import com.example.hibernateexample.exception.UserAlreadyRegisteredException;
import com.example.hibernateexample.service.UserService;
import com.example.hibernateexample.util.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user-register")
    public ResponseEntity<User> register(@RequestBody User user) {
        if (userService.existsByLogin(user.getLogin())){
            throw new UserAlreadyRegisteredException("User already registered");
        }
        if (!PasswordValidator.validatePassword(user.getPassword())){
            throw new BadCredentials("Wrong password");
        }

        User result = userService.save(user);
        return ResponseEntity.ok(result);
    }
}
