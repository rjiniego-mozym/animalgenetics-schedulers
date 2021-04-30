package com.animalgenetics.repositories.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "internal.orders")
@Getter
@Setter
public class Order implements Serializable {

    private static final long serialVersionUID = -7810073475101036033L;

    @Id
    @Column(name = "orderID")
    private int id;

    @ManyToOne
    @JoinColumn(name="orderStatusId", referencedColumnName = "orderStatusId", insertable = false, updatable = false)
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name="orderTypeId", referencedColumnName = "orderTypeId", insertable = false, updatable = false)
    private OrderType type;

    // Convert to user object
    @Column(name = "userID")
    private int userID;

    // Convert to email object
    @Column(name = "emailID")
    private Integer emailID;

    // Convert to address object
    @Column(name = "addressID")
    private Integer addressID;

    // Convert to address object
    @Column(name = "billingAddressID")
    private Integer billingAddressID;

    @Column(name = "hasHold")
    private boolean hasHold;

    @Column(name = "splitPayment")
    private boolean splitPayment;

    @Column(name = "isReady")
    private boolean isReady;

    @Column(name = "comment")
    private String comment;

    @Column(name = "expiredDate")
    private LocalDateTime expiredDate;

}
