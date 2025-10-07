package com.vasos.bikecheck_bot.controller;

import com.vasos.bikecheck_bot.entity.Bike;
import com.vasos.bikecheck_bot.entity.Component;
import com.vasos.bikecheck_bot.service.BikeService;
import com.vasos.bikecheck_bot.service.ComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/bikes/{bikeId}/components")
public class ComponentController {

    private final ComponentService componentService;
    private final BikeService bikeService;

    @Autowired
    public ComponentController(ComponentService componentService, BikeService bikeService){
        this.componentService = componentService;
        this.bikeService = bikeService;
    }

    @PostMapping
    public Component createComponent(@PathVariable Long bikeId, @RequestBody Component component){
        Bike bike = bikeService.getById(bikeId);

        return componentService.createComponent(
                component.getName(),
                component.getPrice(),
                component.getCategory(),
                bike
        );
    }

    @GetMapping
    public List<Component> getComponentsByBike(@PathVariable Long bikeId){
        return componentService.getComponentsByBikeId(bikeId);
    }

    @DeleteMapping
    public String deleteComponentByName(
            @PathVariable Long bikeId,
            @RequestParam String componentName){

            componentService.deleteComponent(componentName,bikeId);
            return "Компонент " + componentName + " удален";
    }



}
