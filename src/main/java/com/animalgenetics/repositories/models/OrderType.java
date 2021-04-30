package com.animalgenetics.repositories.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "internal.ordertype")
@Getter
@Setter
public class OrderType implements Serializable {

    private static final long serialVersionUID = -7810073475101036033L;

    @Id
    @Column(name = "orderTypeID")
    private int id;

    @Column(name = "orderTypeName")
    private String name;

    @OneToMany(targetEntity= Order.class, mappedBy="type",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();

}
