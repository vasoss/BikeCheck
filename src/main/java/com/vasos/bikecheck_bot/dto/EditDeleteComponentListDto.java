package com.vasos.bikecheck_bot.dto;

public class EditDeleteComponentListDto {

    private String componentList;

    public EditDeleteComponentListDto(){}

    public EditDeleteComponentListDto(String componentList){
        this.componentList = componentList;
    }

    public String getComponentList() {
        return componentList;
    }

    public void setComponentList(String componentList) {
        this.componentList = componentList;
    }
}
