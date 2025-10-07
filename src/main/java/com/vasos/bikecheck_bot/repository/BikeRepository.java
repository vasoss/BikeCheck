// Работа с базой данных

package com.vasos.bikecheck_bot.repository;

import com.vasos.bikecheck_bot.entity.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BikeRepository extends JpaRepository<Bike, Long> {
    List<Bike> findByUserId(Long userId);
}
