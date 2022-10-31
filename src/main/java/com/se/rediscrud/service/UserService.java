package com.se.rediscrud.service;

import com.se.rediscrud.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    User save(User user);
    
    User findById(Long id);
    
    void deleteById(Long id);
    
    List<User> getUsers();
    
    User updateUser(Long id, User user);
}
