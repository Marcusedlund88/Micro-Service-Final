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
public class FrontendCustomerController {

    private static final Logger log = LoggerFactory.getLogger(FrontendCustomerController.class);

    @RequestMapping("/customers")
    @ResponseBody
    public ResponseEntity<?> getCustomers(HttpServletRequest request) {
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

    @RequestMapping("/customers/{id}")
    @ResponseBody
    public ResponseEntity<?> getById(@PathVariable long id, HttpServletRequest request) {
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
    @RequestMapping("/customers/{id}/name")
    @ResponseBody
    public ResponseEntity<?> getNameById(@PathVariable long id, HttpServletRequest request) {
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
    @RequestMapping("/customers/{id}/ssn")
    @ResponseBody
    public ResponseEntity<?> getSsnById(@PathVariable long id, HttpServletRequest request) {
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
    @RequestMapping("/customers/{id}/delete")
    @ResponseBody
    public ResponseEntity<?> deleteById(@PathVariable long id, HttpServletRequest request) {
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
                HttpMethod.DELETE,
                requestEntity,
                new ParameterizedTypeReference<Object>() {});

        return ResponseEntity.status(response.getStatusCode()).build();
    }
    @RequestMapping("/customers/{id}/update")
    @ResponseBody
    public ResponseEntity<?> updateById(@PathVariable long id, @RequestBody String requestBody) {
        RestTemplate restTemplate = new RestTemplate();

        String gatewayUrl = "http://localhost:8080";
        String requestUrl = gatewayUrl + "/customers/"+id+"/update";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<String> httpEntity = new HttpEntity<>(requestBody, headers);

        log.info(requestBody);

        ResponseEntity<?> response = restTemplate.exchange(
                requestUrl,
                HttpMethod.PUT,
                httpEntity,
                Void.class);

        return ResponseEntity.status(response.getStatusCode()).build();
    }
}