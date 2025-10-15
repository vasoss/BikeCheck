package com.vasos.bikecheck_bot.controller;

import com.vasos.bikecheck_bot.dto.UserDto;
import com.vasos.bikecheck_bot.entity.User;
import com.vasos.bikecheck_bot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody UserDto userDto){
        return userService.createUser(userDto);
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }


    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/count")
    public Long getUsersCount(){
        return userService.getUsersCount();
    }

}
