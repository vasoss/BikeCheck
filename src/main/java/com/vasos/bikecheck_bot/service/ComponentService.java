package com.vasos.bikecheck_bot.service;

import com.vasos.bikecheck_bot.dto.InstallComponentDto;
import com.vasos.bikecheck_bot.entity.Bike;
import com.vasos.bikecheck_bot.entity.Component;
import com.vasos.bikecheck_bot.repository.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComponentService {

    private final ComponentRepository componentRepository;


    @Autowired
    public ComponentService(ComponentRepository componentRepository){
        this.componentRepository = componentRepository;
    }

    public Component installComponent(Bike bike, InstallComponentDto dto){
        Component component = new Component(dto.getType(), dto.getName(), dto.getPrice());
        component.setBike(bike);
        return componentRepository.save(component);
    }

    public List<Component> getComponentsByBikeId(Long bikeId){
        return componentRepository.findByBikeId(bikeId);
    }

    public Component findComponentByName(String name, Long bikeId){
        return componentRepository.findByNameAndBikeId(name,bikeId);
    }

    public void deleteComponentByType(String type, Long bikeId){
        Component component = componentRepository.findByTypeAndBikeId(type,bikeId);
        componentRepository.delete(component);
    }

    public Integer getComponentPriceByType(String type, Long bikeId){
        Component component = componentRepository.findByTypeAndBikeId(type,bikeId);
        return component.getPrice();
    }

    public Component checkComponentMatching(Long bikeId, String type){
        Component component = componentRepository.findByTypeAndBikeId(type,bikeId);
        return component;
    }




}
