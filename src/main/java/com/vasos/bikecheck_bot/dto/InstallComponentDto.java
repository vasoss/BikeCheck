package com.vasos.bikecheck_bot.dto;

public class InstallComponentDto {

    private String name;
    private String category;
    private Integer price;

    public InstallComponentDto(){}

    public InstallComponentDto(String name, String category, Integer price){
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
