package com.animalgenetics.schedulers;

import com.animalgenetics.repositories.interfaces.OrderRepository;
import com.animalgenetics.repositories.interfaces.OrderTypeRepository;
import com.animalgenetics.repositories.models.Order;
import com.animalgenetics.repositories.models.OrderType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class CheckOpenOrdersScheduler {

    @Value("${checkopenorders.scheduler.enabled}")
    private boolean cronEnabled;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderTypeRepository orderTypeRepository;

    @Scheduled(cron = "${checkopenorders.scheduler.cron}", zone = "${animalgenetics.timezone}")
    public void process() {
        if (cronEnabled) {
            Optional<OrderType> testOrders = orderTypeRepository.findById(1);
            List<Order> orders = orderRepository.findByTypeAndExpiredDateAndIsReady(testOrders.get(), null, false);
            System.out.println("Orders size: " + orders.size());
        }
    }
}
