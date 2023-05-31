package com.example.itemservice;

import com.example.itemservice.Item.Item;
import com.example.itemservice.ItemRepo.ItemRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ItemServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner items (ItemRepo itemRepo){
		return (args) ->{
			itemRepo.save(new Item("Zebrapäls", 500.99));
			itemRepo.save(new Item("Rävpälsjacka", 2500.00));
			itemRepo.save(new Item("Minkpäls", 3000.00));
			itemRepo.save(new Item("Kaninpäls", 1200.00));
			itemRepo.save(new Item("Grävlingspäls", 2200.00));
			itemRepo.save(new Item("Rådjurspäls", 1800.00));
			itemRepo.save(new Item("Vargpäls", 4000.00));
			itemRepo.save(new Item("Fårskinnspäls", 1500.00));
			itemRepo.save(new Item("Bisamråttpäls", 2800.00));
			itemRepo.save(new Item("Hjortskinnspäls", 2200.00));
			itemRepo.save(new Item("Sälpäls", 3500.00));
			itemRepo.save(new Item("Mårhundspäls", 2400.00));
			itemRepo.save(new Item("Fjällrävspäls", 4200.00));
			itemRepo.save(new Item("Ozelotpäls", 6000.00));
			itemRepo.save(new Item("Berguvspäls", 8000.00));
			itemRepo.save(new Item("Bisonoxpäls", 10000.00));
			itemRepo.save(new Item("Murmeldjurspäls", 3000.00));
			itemRepo.save(new Item("Rödrävspäls", 4500.00));
			itemRepo.save(new Item("Ekorrepäls", 2000.00));
			itemRepo.save(new Item("Järvpäls", 5000.00));

		};
	}
}
