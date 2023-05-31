package com.example.frontendservice.FrontendController;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class FrontendOrdersController {



    private static final Logger log = LoggerFactory.getLogger(FrontendOrdersController.class);

    @RequestMapping("/orders")
    @ResponseBody
    public ResponseEntity<?> getOrders(HttpServletRequest request) {
        RestTemplate restTemplate = new RestTemplate();

        String gatewayUrl = "http://localhost:8080";
        String requestUrl = gatewayUrl + request.getRequestURI();

        // Retrieve the token from the request headers
        String token = request.getHeader("Authorization");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<?> response = restTemplate.exchange(
                requestUrl,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<Object>() {});

        Object responseBody = response.getBody();

        return ResponseEntity.ok(responseBody);

    }

    @RequestMapping("/orders/new")
    @ResponseBody
    public ResponseEntity<?> placeOrder(@PathVariable long id, @RequestBody String requestBody) {
        RestTemplate restTemplate = new RestTemplate();

        String gatewayUrl = "http://localhost:8080";
        String requestUrl = gatewayUrl + "/orders/new";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<String> httpEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<?> response = restTemplate.exchange(
                requestUrl,
                HttpMethod.POST,
                httpEntity,
                Void.class);

        return ResponseEntity.status(response.getStatusCode()).build();
    }

}