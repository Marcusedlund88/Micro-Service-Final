package com.example.itemservice.Item;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
public class Item {

    @Id
    @GeneratedValue
    private long id;

    @NotBlank
    @Size(min = 2,max = 30)
    protected String name;

    @NotNull
    @DecimalMin(value ="0.00")
    @DecimalMax(value = "100000.00")
    protected double price;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

}