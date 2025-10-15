package com.vasos.bikecheck_bot.dto;

public class CustomBikeDto {

    private String name;

    public CustomBikeDto(){}

    public CustomBikeDto(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
