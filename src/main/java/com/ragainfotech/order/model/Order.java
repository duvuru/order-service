package com.ragainfotech.order.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="ORDER_TBL")
public class Order {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private int quantity;
    private double price;
}
