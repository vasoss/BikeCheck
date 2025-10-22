package com.vasos.bikecheck_bot.dto;

public class editDeleteComponentListDto {

    private String componentList;

    public editDeleteComponentListDto(){}

    public editDeleteComponentListDto(String componentList){
        this.componentList = componentList;
    }

    public String getComponentList() {
        return componentList;
    }

    public void setComponentList(String componentList) {
        this.componentList = componentList;
    }
}
