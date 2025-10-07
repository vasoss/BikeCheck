package com.vasos.bikecheck_bot.service;

import com.vasos.bikecheck_bot.entity.Bike;
import com.vasos.bikecheck_bot.entity.User;
import com.vasos.bikecheck_bot.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(() ->new EntityNotFoundException("Пользователь не найден с id: " + id));
    }





    public User createUser(Long id, String name){
        if(userRepository.existsById(id)){
            return userRepository.findById(id).get();
        }

        User newUser = new User(id,name);
        return userRepository.save(newUser);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Long getUsersCount(){
        return  userRepository.count();
    }



}
