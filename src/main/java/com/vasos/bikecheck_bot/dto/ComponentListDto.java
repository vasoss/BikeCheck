package com.vasos.bikecheck_bot.dto;

public class ComponentListDto {

    private String componentList;

    public ComponentListDto(){}

    public ComponentListDto(String componentList){
        this.componentList = componentList;
    }

    public String getComponentList() {
        return componentList;
    }

    public void setComponentList(String componentList) {
        this.componentList = componentList;
    }
}
