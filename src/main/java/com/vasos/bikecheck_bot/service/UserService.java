package com.vasos.bikecheck_bot.service;

import com.vasos.bikecheck_bot.dto.UserDto;
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


    public User createUser(UserDto userDto){
        if(userRepository.existsById(userDto.getId())){
            return userRepository.findById(userDto.getId()).get();
        }
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        return userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Long getUsersCount(){
        return  userRepository.count();
    }



}
