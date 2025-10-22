// Бизнес логика

package com.vasos.bikecheck_bot.service;

import com.vasos.bikecheck_bot.dto.*;
import com.vasos.bikecheck_bot.entity.Bike;
import com.vasos.bikecheck_bot.entity.User;
import com.vasos.bikecheck_bot.repository.BikeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
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

    public Bike createStockBike(Long userId, StockBikeDto dto){
        Bike bike = new Bike();
        User user = userService.getUserById(userId);
        bike.setName(dto.getName());
        bike.setPrice(dto.getPrice());
        bike.setInvest(0);
        bike.setUser(user);
        user.setBikesCount(user.getBikesCount()+1);
        return bikeRepository.save(bike);
    }

    public void installComponent(Long id, InstallComponentDto dto){
        Bike bike = getById(id);
        componentService.createComponent(bike,dto);
        //addPrice(id, dto.getPrice());
        bikeRepository.save(getById(id));
    }


    public void editInstallComponentList(Long id, ComponentListDto componentList){
        Bike bike = getById(id);
        Integer componentSum = 0;
        String[] components = componentList.getComponentList().split("\n");
        for(String component : components){
            String[] part = component.split(",");
            String partType = part[0].trim();
            String partName = String.join(",",Arrays.copyOfRange(part,1,part.length-1)).trim();
            Integer partPrice = Integer.parseInt(part[part.length-1].replaceAll(" ",""));
            InstallComponentDto dto = new InstallComponentDto(partType,partName,partPrice);
            componentService.createComponent(bike,dto);
            componentSum += partPrice;
        }
        bike.adjustPrice(+componentSum);
        bikeRepository.save(bike);
    }

    public void editDeleteComponentList(Long id, editDeleteComponentListDto dto){
        Bike bike = getById(id);
        Integer componentSum = 0;
        String[] components = dto.getComponentList().split(",");
        for(String component : components){
            String componentType = component.toLowerCase().trim();
            Integer price = componentService.getComponentPriceByType(componentType,id);
            componentSum += price;
            componentService.deleteComponentByType(componentType,id);
        }
        bike.adjustPrice(-componentSum);
        bikeRepository.save(bike);
    }



    public void setPrice(Long bikeId, Integer bikePrice){
        getById(bikeId).setPrice(bikePrice);
    }
    public Integer getPrice(Long bikeId){
        return  getById(bikeId).getPrice();
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
