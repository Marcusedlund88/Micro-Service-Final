package com.example.customerservice.CustomerRepo;

import com.example.customerservice.Customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer,Long> {
}
