package com.animalgenetics.repositories.interfaces;

import com.animalgenetics.repositories.models.Order;
import com.animalgenetics.repositories.models.OrderType;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Integer> {

    public List<Order> findByTypeAndExpiredDateAndIsReady(OrderType typeID, LocalDateTime expiredDate, boolean isReady);
    
}