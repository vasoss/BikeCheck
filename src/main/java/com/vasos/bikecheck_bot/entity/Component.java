package com.vasos.bikecheck_bot.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "components")
public class Component {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Integer price;

    @Column(name = "type")
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)  //Связь с велосипедом (многие компоненты к одному велосипеду)
    @JoinColumn(name = "bike_id")
    @JsonBackReference
    private Bike bike;



    public Component(){}

    public Component(String type, String name, Integer price){
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public Integer getPrice(){
        return price;
    }
    public void setPrice(Integer price){
        this.price = price;
    }

    public String getType(){
        return type;
    }
    public void setType(String category){
        this.type = category;
    }

    public Bike getBike(){
        return bike;
    }

    public void setBike(Bike bike) {
        this.bike = bike;
    }



}
