package com.example.project.service;

import com.example.project.Model.ApplicationUser;
import com.example.project.repository.ApplicationUserRepository;
import com.example.project.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.json.simple.JSONObject;

@Service
public class ApplicationUserService {

    @Autowired
    private ApplicationUserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public JSONObject registerUser(ApplicationUser user) {
        JSONObject response = new JSONObject();
        // Check if user already exists
        if(userRepo.existsById(user.getUserName())){
            response.put("message", "Password or username policy failed");
            return response;
        }
        // Encrypt password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        ApplicationUser savedUser = userRepo.save(user);
        response.put("message", "Registration successful");
        System.out.println("Saved User" + savedUser);
        return response;
    }

    public JSONObject authenticateUser(ApplicationUser user) {
        System.out.println("User Controller");
        JSONObject response = new JSONObject();
        System.out.println("Response: " + response);
        ApplicationUser existingUser = userRepo.findById(user.getUserName()).orElse(null);
        System.out.println("existing user" );
        System.out.println(existingUser);
        if(existingUser == null || !passwordEncoder.matches(user.getPassword(), existingUser.getPassword())){
            response.put("message", "Username or Password is Incorrect.");
            return response;
        }
        String token = jwtUtil.generateToken(existingUser);
        response.put("message", "Authentication successful!");
        response.put("token", token);
        response.put("id", existingUser.getUserName());
        return response;
    }

    public ApplicationUser findUserById(String userId) {
        ApplicationUser searchedUser =  userRepo.findById(userId).orElse(null);
        System.out.println("Searched User => " + searchedUser);
        return searchedUser;
    }
}
