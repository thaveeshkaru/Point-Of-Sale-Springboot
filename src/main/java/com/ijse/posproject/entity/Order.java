package com.ijse.posproject.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private LocalDateTime orderDateTime;
    private double discount;
    private double totalPrice;

    @PrePersist
    protected void onCreate(){
        this.orderDateTime = LocalDateTime.now();
    }

    private String paymentMethod;

    @ManyToMany
    @JoinTable(
        name= "order_item",
        joinColumns = @JoinColumn(name= "orderId"),
        inverseJoinColumns = @JoinColumn(name = "itemCode")
    )

    private List<Item> orderedItems;

}
