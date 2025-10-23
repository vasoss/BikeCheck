package com.vasos.bikecheck_bot.dto;

public class EditInstallComponentListDto {

    private String componentList;

    public EditInstallComponentListDto(){}

    public EditInstallComponentListDto(String componentList){
        this.componentList = componentList;
    }

    public String getComponentList() {
        return componentList;
    }

    public void setComponentList(String componentList) {
        this.componentList = componentList;
    }
}
