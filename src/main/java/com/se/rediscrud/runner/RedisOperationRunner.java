//package com.se.rediscrud.runner;
//
//import com.se.rediscrud.model.Role;
//import com.se.rediscrud.model.User;
//import com.se.rediscrud.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.Map;
//
//@Component
//public class RedisOperationRunner implements CommandLineRunner {
//    
//    @Autowired
//    private UserService userService;
//    
//    @Override
//    public void run(String... args) throws Exception {
//        userService.save(new User(1L, "user1", "vip", Role.USER));
//        
//        userService.saveAllUsers(
//                Map.of(
//                        2L, new User(2L, "user2", "vip", Role.USER),
//                        3L, new User(3L, "user3", "vip", Role.USER),
//                        4L, new User(4L, "user4", "vip", Role.USER),
//                        5L, new User(5L, "user5", "vip", Role.USER)
//                )
//        );
//        //update user 1l
//        userService.updateUser(new User(1L, "user1", "vip", Role.ADMIN));
//        
//        //delete user 2l
//        userService.deleteById(2L);
//        
//        //get all users
//        userService.getUsers().forEach((k, v) -> System.out.println(k + " " + v));
//        
//        System.out.println("user 1: " + userService.findById(1L));
//    }
//}
