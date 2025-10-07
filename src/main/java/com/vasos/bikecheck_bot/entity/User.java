package com.vasos.bikecheck_bot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    private Long id;

    @Column(name = "user_name")
    private String name;

    @Column(name = "bikes_count")
    private Integer bikesCount = 0;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Bike> userBikes = new ArrayList<>();

    public User(){}

    public User(Long id, String name){
        this.id = id;
        this.name = name;
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

    public Integer getBikesCount(){
        return bikesCount;
    }
    public void setBikesCount(Integer bikesCount){
        this.bikesCount = bikesCount;
    }

    public List<Bike> getUserBikes() {
        return userBikes;
    }
    public void setUserBikes(List<Bike> userBikes) {
        this.userBikes = userBikes;
    }
}
