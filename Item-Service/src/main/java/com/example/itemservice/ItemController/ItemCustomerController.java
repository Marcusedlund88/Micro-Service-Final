package com.example.itemservice.ItemController;

import com.example.itemservice.Item.Item;
import com.example.itemservice.ItemRepo.ItemRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/items")
@CrossOrigin(origins = "http://localhost:9090")
public class ItemCustomerController {

    private static final Logger log = LoggerFactory.getLogger(ItemCustomerController.class);
    ItemRepo itemRepo;

    public ItemCustomerController(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }

    @RequestMapping("")
    @ResponseBody
    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 1000))

    public List<Item> get(){
        return itemRepo.findAll();
    }

    @RequestMapping("/{id}")
    @ResponseBody
    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 1000))

    public Item getById(@PathVariable long id){

        return itemRepo.findById(id).get();
    }

    @RequestMapping("/{id}/name")
    @ResponseBody
    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 1000))

    public ResponseEntity<Object> getNameById(@PathVariable long id){
        Item item = itemRepo.findById(id).get();
        String name = item.getName();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = objectMapper.writeValueAsString(name);
            log.info(jsonString);
            return ResponseEntity.ok(jsonString);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/{id}/price")
    @ResponseBody
    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 1000))

    public ResponseEntity<Object> getPriceById(@PathVariable long id){
        Item item = itemRepo.findById(id).get();
        double price = item.getPrice();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = objectMapper.writeValueAsString(price);
            log.info(jsonString);
            return ResponseEntity.ok(jsonString);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
