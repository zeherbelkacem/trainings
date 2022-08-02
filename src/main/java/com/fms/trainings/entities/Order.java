package com.fms.trainings.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "ORDERS")
public class Order implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ORDER_NB")
    private long orderNb;

    @Column(name = "DATE")
    private Date date;

    @Column(name = "TOTAL_PRICE")
    private double totalPrice;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    @ManyToOne
    @JoinColumn(name = "customer_ID")
    private Customer customer;
}
