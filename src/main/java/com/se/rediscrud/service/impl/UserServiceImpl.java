package com.se.rediscrud.service.impl;

import com.se.rediscrud.model.Role;
import com.se.rediscrud.model.User;
import com.se.rediscrud.repository.UserRepository;
import com.se.rediscrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public User save(User user) {
        return this.userRepository.save(user);
    }
    
    @Override
    @Cacheable(value = "User", key = "#id")
    public User findById(Long id) {
        return this.userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User not found with id: " + id)
        );
    }
    
    @Override
    @CacheEvict(value = "User", key = "#id")
    public void deleteById(Long id) {
        User user = this.userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User not found with id: " + id)
        );
        this.userRepository.delete(user);
    }
    
    @Override
    @Cacheable(value = "User")
    public List<User> getUsers() {
        return this.userRepository.findAll();
    }
    
    
    @Override
    @CachePut(value = "User", key = "#id")
    public User updateUser(Long id, User user) {
        User existingUser = this.userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User not found with id: " + id)
        );
        existingUser.setUsername(user.getUsername());
        existingUser.setRole(user.getRole());
        existingUser.setPassword(user.getPassword());
        return this.userRepository.save(existingUser);
    }
}
