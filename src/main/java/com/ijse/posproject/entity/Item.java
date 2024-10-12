package com.ijse.posproject.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemCode;

    private String itemName;

    private String description;

    private Double price;

    @ManyToOne
    @JoinColumn( name = "CategoryId")
    private Category category;

    @JsonIgnore
    @OneToMany( mappedBy = "item") 
    private List<Stock> stocks; 
    
    @JsonIgnore
    @ManyToMany(mappedBy = "orderedItems")
    private List<Order> orders;


}
