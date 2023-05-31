package com.example.orderservice.Orders;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
public class Orders {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    protected long customerId;

    @NotEmpty
    protected List<Long> itemIds;

    public Orders(long customerId, List<Long> itemIds) {
        this.customerId = customerId;
        this.itemIds = itemIds;
    }

    //protected double totalCost;

    // protected LocalDate localDate;
}