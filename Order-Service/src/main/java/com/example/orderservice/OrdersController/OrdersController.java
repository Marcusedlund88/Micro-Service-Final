package com.example.orderservice.OrdersController;

import com.example.orderservice.Orders.Orders;
import com.example.orderservice.OrdersRepo.OrdersRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.*;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:9090")
public class OrdersController {

    OrdersRepo orderRepo;

    public OrdersController(OrdersRepo orderRepo){
        this.orderRepo = orderRepo;
    }
    private static final Logger log = LoggerFactory.getLogger(OrdersController.class);
    @GetMapping("")
    @ResponseBody
    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 1000))


    public List<Orders> getOrders(){
        return orderRepo.findAll();
    }

    @GetMapping("/info/{id}")
    @ResponseBody
    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 1000))

    public String customerNameByOrderId(@PathVariable long id, @RequestHeader("Authorization") String token){

        Orders orderToVerify = orderRepo.findById(id).get();
        Long customerId = orderToVerify.getCustomerId();

        log.info("ok");
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<?> entity = new HttpEntity<>(headers);


        ResponseEntity<String> responseCustomer = restTemplate.exchange(
                "http://gateway-micro:8080/customers/{customerId}/name",
                HttpMethod.GET,
                entity,
                String.class,
                customerId
        );
        log.info(responseCustomer.toString());

        String username = responseCustomer.getBody();

        List<String> itemsToEvaluate = new ArrayList<>();

        orderToVerify.getItemIds().forEach(item->{
            ResponseEntity<String> responseItem = restTemplate.exchange(
                    "http://gateway-micro:8080/items/{item}",
                    HttpMethod.GET,
                    entity,
                    String.class,
                    item
            );
            log.info(responseItem.toString());

            String itemName = responseItem.getBody();
            itemsToEvaluate.add(itemName);
        });

        log.info(username);

        return username + " " + itemsToEvaluate.toString();
    }

    @PostMapping("/new")
    @ResponseBody
    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 1000))

    public Orders makeNewOrder(@RequestBody Orders orders, @RequestHeader("Authorization") String token) {

        RestTemplate restTemplate = new RestTemplate();
        long customerId = orders.getCustomerId();

        log.info("test");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<Object> customerResponse;
        try {
            customerResponse = restTemplate.exchange(
                    "http://gateway-micro:8080/customers/{customerId}",
                    HttpMethod.GET,
                    entity,
                    Object.class,
                    customerId
            );
        } catch (HttpClientErrorException.NotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer with ID " + customerId + " does not exist.");
        }

        if (customerResponse.getStatusCode() != HttpStatus.OK) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve customer with ID " + customerId);
        }

        Object customer = customerResponse.getBody();

        List<Long> itemsToEvaluate = orders.getItemIds();

        for (Long itemId : itemsToEvaluate) {
            ResponseEntity<Object> itemResponse;
            try {
                itemResponse = restTemplate.exchange(
                        "http://gateway-micro:8080/items/{itemId}",
                        HttpMethod.GET,
                        entity,
                        Object.class,
                        itemId
                );
            } catch (HttpClientErrorException.NotFound e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item with ID " + itemId + " does not exist.");
            }

            if (itemResponse.getStatusCode() != HttpStatus.OK) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve item with ID " + itemId);
            }

            Object item = itemResponse.getBody();

            // Use the retrieved item object as needed
        }

        log.info("hello");
        Orders order = new Orders(orders.getCustomerId(), orders.getItemIds());
        orderRepo.save(order);
        return order;
    }



    @ExceptionHandler (NullPointerException.class)
    public ResponseEntity<String> handleException(NullPointerException e) {
        return ResponseEntity.badRequest().body("Customer or items not found");
    }

    @PutMapping("/{id}/update")
    @ResponseBody
    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 1000))

    public Orders updateOrder(@PathVariable long id){
        Orders orderToUpdate = orderRepo.findById(id).get();
        return orderToUpdate;
    }
}