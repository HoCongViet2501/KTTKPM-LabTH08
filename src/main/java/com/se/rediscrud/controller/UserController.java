package com.se.rediscrud.controller;

import com.se.rediscrud.model.User;
import com.se.rediscrud.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/users")
@Log4j2
public class UserController {
    
    @Autowired
    private UserService userService;
    
    private int count= 1;
    
    @GetMapping
    @CircuitBreaker(name = "user", fallbackMethod = "fallback")
    @Retry(name="user")
    public ResponseEntity<Object> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }
    
    @GetMapping("{id}")
   // @CircuitBreaker(name = "user", fallbackMethod = "fallback")
   // @Retry(name="user",fallbackMethod = "fallback")
    @RateLimiter(name = "user")
    public ResponseEntity<Object> getUserById(@PathVariable Long id) {
       // log.info("retry count: " + count++ +" times at " + new Date());
        return ResponseEntity.ok().body(userService.findById(id));
    }
    
    @PostMapping
    @CircuitBreaker(name = "user", fallbackMethod = "fallback")
    @Retry(name="user")
    public ResponseEntity<Object> saveUser(@RequestBody User user) {
        log.info("retry count: " + count++ +" times at " + new Date());
        return ResponseEntity.ok().body(userService.save(user));
    }
    
    @DeleteMapping("{id}")
    @CircuitBreaker(name = "user", fallbackMethod = "fallback")
    @Retry(name="user")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        log.info("retry count: " + count++ +" times");
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("{id}")
    @CircuitBreaker(name = "user", fallbackMethod = "fallback")
    @Retry(name="user")
    public ResponseEntity<Object> updateUser(@PathVariable Long id, @RequestBody User user) {
        return ResponseEntity.ok().body(userService.updateUser(id, user));
    }
    
    public ResponseEntity<Object> fallback(Exception e) {
        return ResponseEntity.internalServerError().body("Fallback method called");
    }
}
