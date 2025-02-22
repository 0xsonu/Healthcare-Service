package com.example.project.controller;

import com.example.project.Model.ApplicationUser;
import com.example.project.service.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.json.simple.JSONObject;

@RestController
public class ApplicationUserController {

    @Autowired
    private ApplicationUserService applicationUserService;

    @PostMapping("/register")
    public JSONObject registerUser(@RequestBody ApplicationUser user) {
        return applicationUserService.registerUser(user);
    }

    @PostMapping("/signin")
    public JSONObject authenticateUser(@RequestBody ApplicationUser user) {
        return applicationUserService.authenticateUser(user);
    }

    @GetMapping("/viewprofile/{userId}")
    public ApplicationUser viewProfile(@PathVariable("userId") String userId) {
        return applicationUserService.findUserById(userId);
    }
}
