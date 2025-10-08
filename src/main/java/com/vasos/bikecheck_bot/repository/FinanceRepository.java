package com.vasos.bikecheck_bot.repository;

import com.vasos.bikecheck_bot.entity.Finance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinanceRepository extends JpaRepository<Finance, Long > {

}
