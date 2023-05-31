package com.example.itemservice.ItemRepo;

import com.example.itemservice.Item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<Item,Long> {
}
