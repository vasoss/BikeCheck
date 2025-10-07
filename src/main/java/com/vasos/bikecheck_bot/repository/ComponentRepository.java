package com.vasos.bikecheck_bot.repository;

import com.vasos.bikecheck_bot.entity.Component;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComponentRepository extends JpaRepository<Component, Long> {
    List<Component> findByBikeId(Long bikeId);
    Component findByNameAndBikeId(String name, Long bikeId);

}
