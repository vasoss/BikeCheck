// Принимаем http запросы

package com.vasos.bikecheck_bot.controller;

import com.vasos.bikecheck_bot.dto.*;
import com.vasos.bikecheck_bot.entity.Bike;
import com.vasos.bikecheck_bot.entity.Component;
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

    @PostMapping("/create/custom")
    public Bike createCustomBike(@PathVariable Long userId,
                                 @RequestBody CustomBikeDto dto){
        return bikeService.createCustomBike(userId, dto);
    }

    @PostMapping("/create/stock")
    public Bike createStockBike(@PathVariable Long userId,
                                @RequestBody StockBikeDto dto){
        return bikeService.createStockBike(userId, dto);
    }

    @PostMapping("/{bikeId}/edit/install")
    public void editInstallComponentList(@PathVariable Long bikeId,
                                     @RequestBody EditInstallComponentListDto dto){
        bikeService.editInstallComponentList(bikeId,dto);
    }

    @DeleteMapping("/{bikeId}/edit/delete")
    public void editDeleteComponentList(@PathVariable Long bikeId,
                                        @RequestBody EditDeleteComponentListDto dto){
        bikeService.editDeleteComponentList(bikeId,dto);
    }

    @PostMapping("/{bikeId}/upgrade/install")
    public Component upgradeInstallComponent(@PathVariable Long bikeId,
                                             @RequestBody InstallComponentDto dto){
        return bikeService.upgradeInstallComponent(bikeId,dto);
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

    @PostMapping("/{bikeId}/install")
    public void installComponent(@PathVariable Long bikeId,
                                 @RequestBody InstallComponentDto component){
        bikeService.installComponent(bikeId, component);
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