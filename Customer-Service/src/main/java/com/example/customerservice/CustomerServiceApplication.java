package com.example.customerservice;

import com.example.customerservice.Customer.Customer;
import com.example.customerservice.CustomerRepo.CustomerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner customers(CustomerRepo customerRepo) {
        return (args) -> {
            customerRepo.save(new Customer("Johan Andersson", "890214-1234"));
            customerRepo.save(new Customer("Sofia Lundqvist", "810730-5678"));
            customerRepo.save(new Customer("Marcus Bergström", "950815-9012"));
            customerRepo.save(new Customer("Emma Eriksson", "880601-2345"));
            customerRepo.save(new Customer("Alexander Dahlberg", "930505-6789"));
            customerRepo.save(new Customer("Hanna Lindqvist", "920423-0123"));
            customerRepo.save(new Customer("Erik Nilsson", "960101-3456"));
            customerRepo.save(new Customer("Malin Jönsson", "870727-7890"));
            customerRepo.save(new Customer("Andreas Gustavsson", "940307-1235"));
            customerRepo.save(new Customer("Amanda Karlsson", "900412-6781"));
            customerRepo.save(new Customer("Isabella Svensson", "910610-9012"));
            customerRepo.save(new Customer("Simon Johansson", "980211-2345"));
            customerRepo.save(new Customer("Maria Åberg", "860823-5678"));
            customerRepo.save(new Customer("David Persson", "960924-1234"));
            customerRepo.save(new Customer("Linnea Berglund", "920328-7890"));
            customerRepo.save(new Customer("Oscar Lundin", "930827-0123"));
            customerRepo.save(new Customer("Josefine Holm", "900214-5678"));
            customerRepo.save(new Customer("Emil Pettersson", "890607-2345"));
            customerRepo.save(new Customer("Alice Lundberg", "920915-7890"));
            customerRepo.save(new Customer("Victor Eklund", "950413-0123"));
        };
    }
}
