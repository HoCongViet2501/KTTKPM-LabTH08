package com.se.rediscrud.controller;

import com.se.rediscrud.model.User;
import com.se.rediscrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping
    public ResponseEntity<Object> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }
    
    @GetMapping("{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.findById(id));
    }
    
    @PostMapping
    public ResponseEntity<Object> saveUser(@RequestBody User user) {
        return ResponseEntity.ok().body(userService.save(user));
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Long id, @RequestBody User user) {
        return ResponseEntity.ok().body(userService.updateUser(id, user));
    }
}
