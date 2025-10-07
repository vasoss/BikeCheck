// Создали таблицу

package com.vasos.bikecheck_bot.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bikes")

public class Bike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Integer price;

    @Column(name = "invest")
    private Integer invest;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "bike", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Component> components = new ArrayList<>();






    public Bike(){
        //Пустой конструктор без параметров для Hibernate
    }

    public Bike(String name){
        this.name = name;
    }

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user){
        this.user = user;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public void setPrice(Integer price){
        this.price = price;
    }
    public Integer getPrice(){
        return price;
    }
    public void addPrice(Integer price){
        this.price += price;
    }
    public void subtractPrice(Integer price){
        this.price -= price;
    }

    public void setInvest(Integer invest){
        this.invest = invest;
    }
    public Integer getInvest(){
        return invest;
    }
    public void addInvest(Integer invest){
        this.invest += invest;
    }
    public void subtractInvest(Integer invest){
        this.invest -= invest;
    }



    public List<Component> getComponents(){
        return components;
    }
    public void setComponents(List<Component> components){
        this.components = components;
    }

}