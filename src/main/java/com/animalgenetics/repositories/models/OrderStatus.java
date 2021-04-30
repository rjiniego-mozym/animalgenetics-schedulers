package com.animalgenetics.repositories.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "internal.orderstatus")
@Getter
@Setter
public class OrderStatus implements Serializable {

    private static final long serialVersionUID = -7810073475101036033L;

    @Id
    @Column(name = "orderStatusID")
    private int id;

    @Column(name = "orderStatusName")
    private String name;

    @OneToMany(targetEntity= Order.class, mappedBy="status",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();

}
