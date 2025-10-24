package com.vasos.bikecheck_bot.dto;

public class UpgradeUninstallComponentDto {

    private String type;
    private Integer sellPrice;

    UpgradeUninstallComponentDto(){}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Integer sellPrice) {
        this.sellPrice = sellPrice;
    }
}
