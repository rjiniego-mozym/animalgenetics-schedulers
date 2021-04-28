package com.animalgenetics.schedulers;

import com.animalgenetics.repositories.Orders;
import com.animalgenetics.repositories.OrdersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class CheckOpenOrdersScheduler {

    @Value("${checkopenorders.scheduler.enabled}")
    private boolean cronEnabled;

    @Autowired
    private OrdersRepository ordersRepository;

    @Scheduled(cron = "${checkopenorders.scheduler.cron}", zone = "${animalgenetics.timezone}")
    public void process() {
        if (cronEnabled) {
            Optional<Orders> byId = ordersRepository.findById(38594);
            if(byId.isPresent()) {
                System.out.println(byId.get().getOrderID());
            } else {
                System.out.println("Nope");
            }
        } else {
        }
    }
}
