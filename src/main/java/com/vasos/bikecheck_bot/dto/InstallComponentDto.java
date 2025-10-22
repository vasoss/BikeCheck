package com.vasos.bikecheck_bot.dto;

public class InstallComponentDto {

    private String name;
    private String type;
    private Integer price;

    public InstallComponentDto(){}

    public InstallComponentDto(String type, String name, Integer price){
        this.type = type;
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
