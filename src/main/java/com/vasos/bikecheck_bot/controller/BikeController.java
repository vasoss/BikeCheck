// Принимаем http запросы

package com.vasos.bikecheck_bot.controller;

import com.vasos.bikecheck_bot.dto.BikeDto;
import com.vasos.bikecheck_bot.entity.Bike;
import com.vasos.bikecheck_bot.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/bikes") //Базовый путь для всех методов
public class BikeController {

    private final BikeService bikeService;

    @Autowired
    public BikeController(BikeService bikeService) {
        this.bikeService = bikeService;
    }

    @GetMapping
    public List<BikeDto> getUserBikes(@PathVariable Long userId){
        return bikeService.getUserBikes(userId);
    }

    @PostMapping
    public Bike createBike(@RequestBody String name,
                           @PathVariable Long userId){
        return bikeService.createBike(name,userId);
    }

    @GetMapping("/{id}")
    public Bike getBikeById(@PathVariable Long id){
        return bikeService.getById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteBikeById(@PathVariable Long id){
        bikeService.deleteBike(id);
        return "Велосипед с id " + id + " удален";
    }


    @GetMapping("/count")
    public Long getBikesCount(){
        return bikeService.getCount();
    }



}