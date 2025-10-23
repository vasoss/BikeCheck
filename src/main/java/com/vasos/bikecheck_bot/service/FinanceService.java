package com.vasos.bikecheck_bot.service;

import com.vasos.bikecheck_bot.entity.Bike;
import com.vasos.bikecheck_bot.entity.Finance;
import com.vasos.bikecheck_bot.repository.FinanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinanceService {

    private final FinanceRepository financeRepository;


    @Autowired
    public FinanceService(FinanceRepository financeRepository){
        this.financeRepository = financeRepository;
    }

    public void installTransaction(Bike bike, Integer price, String componentName, String componentType){
        Finance transaction = new Finance();
        transaction.setPurchasePrice(price);
        transaction.setType("BUY");
        transaction.setDescription("Покупка : " + componentType + " - " + componentName);
        transaction.setBike(bike);
        financeRepository.save(transaction);
    }



}
