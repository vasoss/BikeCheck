package com.vasos.bikecheck_bot.service;

import com.vasos.bikecheck_bot.entity.Bike;
import com.vasos.bikecheck_bot.entity.Component;
import com.vasos.bikecheck_bot.repository.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComponentService {

    private final ComponentRepository componentRepository;
    private final BikeService bikeService;

    @Autowired
    public ComponentService(ComponentRepository componentRepository, BikeService bikeService){
        this.componentRepository = componentRepository;
        this.bikeService = bikeService;
    }

    public Component createComponent(String name, Double price, String category, Long bikeId){
        Bike bike = bikeService.getById(bikeId);
        Component component = new Component(name,price,category);
        component.setBike(bike);
        return componentRepository.save(component);
    }

    public List<Component> getComponentsByBikeId(Long bikeId){
        return componentRepository.findByBikeId(bikeId);
    }

    public Component findComponentByName(String name, Long bikeId){
        return componentRepository.findByNameAndBikeId(name,bikeId);
    }

    public void deleteComponent(String name, Long bikeId){
        componentRepository.delete(findComponentByName(name, bikeId));
    }




}
