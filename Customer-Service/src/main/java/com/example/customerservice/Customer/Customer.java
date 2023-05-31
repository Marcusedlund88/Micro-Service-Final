package com.example.customerservice.Customer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
public class Customer {

    @Id
    @GeneratedValue
    private long id;

    @NotBlank
    @Size(min = 2,max = 30)
    protected String name;

    @NotBlank
    @Size(min = 2,max = 30)
    protected String ssn;


    public Customer(String name, String ssn){
        this.name = name;
        this.ssn = ssn;
    }
}
