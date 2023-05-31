package com.example.frontendservice.FrontendController;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class FrontendSecurityController {

    private static final Logger log = LoggerFactory.getLogger(FrontendSecurityController.class);

    @RequestMapping("/authenticate")
    @ResponseBody
    public ResponseEntity<String> getToken(HttpServletRequest request, @RequestBody(required = false) String requestBody) {
        RestTemplate restTemplate = new RestTemplate();

        String gatewayUrl = "http://localhost:8080";
        String requestUrl = gatewayUrl + request.getRequestURI();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        log.info(requestBody);

        ResponseEntity<String> response = restTemplate.exchange(
                requestUrl,
                HttpMethod.POST,
                requestEntity,
                String.class);

        String token = response.getBody();
        log.info(token);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(token);
    }


}