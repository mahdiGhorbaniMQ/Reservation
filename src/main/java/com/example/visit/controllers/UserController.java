package com.example.visit.controllers;

import com.example.visit.models.User;
import com.example.visit.payload.response.MessageResponse;
import com.example.visit.payload.response.UserProfileResponse;
import com.example.visit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(Principal principal, @RequestBody User newUser) {
        String username = principal.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        user.setName(newUser.getName());
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("user successfully updated!"));
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile(@RequestParam String email){
        User user = userRepository.findByUsername(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + email));

        return ResponseEntity.ok(new UserProfileResponse(user.getName(), user.getEmail()));
    }
}
