// Бизнес логика

package com.vasos.bikecheck_bot.service;

import com.vasos.bikecheck_bot.dto.BikeDto;
import com.vasos.bikecheck_bot.dto.CustomBikeDto;
import com.vasos.bikecheck_bot.dto.InstallComponentDto;
import com.vasos.bikecheck_bot.entity.Bike;
import com.vasos.bikecheck_bot.entity.User;
import com.vasos.bikecheck_bot.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BikeService {

    private final BikeRepository bikeRepository;
    private final UserService userService;
    private final ComponentService componentService;

    @Autowired
    public BikeService(BikeRepository bikeRepository, UserService userService, ComponentService componentService){
        this.bikeRepository = bikeRepository;
        this.userService = userService;
        this.componentService = componentService;
    }

    public Bike createCustomBike(Long userId, CustomBikeDto dto){
        Bike bike = new Bike(dto.getName());
        User user = userService.getUserById(userId);
        bike.setUser(user);
        bike.setPrice(0);
        bike.setInvest(0);
        user.setBikesCount(user.getBikesCount()+1);
        return bikeRepository.save(bike);
    }

    public void installComponent(Long id, InstallComponentDto dto){
        Bike bike = getById(id);
        componentService.createComponent(bike,dto.getCategory(), dto.getName(), dto.getPrice());
        bike.addPrice(dto.getPrice());
    }




    public void setPrice(Long bikeId, Integer bikePrice){
        getById(bikeId).setPrice(bikePrice);
    }
    public Integer getPrice(Long bikeId){
        return  getById(bikeId).getPrice();
    }
    public void addPrice(Long bikeId, Integer price){
        getById(bikeId).addPrice(price);
    }
    public void subtractPrice(Long bikeId, Integer price){
        getById(bikeId).subtractPrice(price);
    }

    public void setInvest(Long bikeId, Integer bikeInvest){
        getById(bikeId).setInvest(bikeInvest);
    }
    public Integer getInvest(Long bikeId){
        return getById(bikeId).getInvest();
    }
    public void addInvest(Long bikeId, Integer invest){
        getById(bikeId).addInvest(invest);
    }
    public void subtractInvest(Long bikeId, Integer invest){
        getById(bikeId).subtractInvest(invest);
    }


    public Bike createBike(String name, Long userId){
        User user = userService.getUserById(userId);
        Bike bike = new Bike();
        bike.setName(name);
        bike.setUser(user);
        user.setBikesCount(user.getBikesCount()+1);
        return bikeRepository.save(bike);
    }

    public List<Bike> getAllBikes(){
        return bikeRepository.findAll();
    }

    public List<BikeDto> getUserBikes(Long userId){
        List<Bike> bikes = bikeRepository.findByUserId(userId);
        List<BikeDto> bikeDtos = new ArrayList<>();
        for(Bike bike : bikes){
            BikeDto dto = new BikeDto();
            dto.setId(bike.getId());
            dto.setName(bike.getName());
            dto.setUserName(userService.getUserById(userId).getName());
            bikeDtos.add(dto);
        }
        return bikeDtos;
    }

    public Bike getById(Long id){
        return bikeRepository.findById(id).orElseThrow(() -> new RuntimeException("Велосипед не найден с id: " + id));
    }

    public void deleteBike(Long id){
        if(bikeRepository.existsById(id)){
            bikeRepository.deleteById(id);
        }else {
            throw new RuntimeException("Велосипед не найден с id: "+ id);
        }
    }

//    public List<Bike> getUserBikes(User user){
//        return bikeRepository.findByUserId(user.getId());
//    }


    public Long getCount(){
        return bikeRepository.count();
    }


}
