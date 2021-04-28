package com.animalgenetics.repositories;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "internal.orders")
@Getter
@Setter
public class Orders implements Serializable {

    private static final long serialVersionUID = -7810073475101036033L;

    @Id
    @Column(name = "orderID")
    private int orderID;

}
