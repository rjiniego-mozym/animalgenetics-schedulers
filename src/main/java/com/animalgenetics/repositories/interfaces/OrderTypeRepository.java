package com.animalgenetics.repositories.interfaces;

import com.animalgenetics.repositories.models.OrderType;
import org.springframework.data.repository.CrudRepository;

public interface OrderTypeRepository extends CrudRepository<OrderType, Integer> {

}