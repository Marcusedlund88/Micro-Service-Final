package com.example.gatewayservice.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);
    public final List<String> openEndpoints = List.of(
            "/authenticate",
            "/register"
    );

    public Predicate<ServerHttpRequest> isSecure() {
        return request -> {
            String endpoint = request.getURI().getPath();
            log.info("Checking endpoint: {}", endpoint);
            return openEndpoints.stream().noneMatch(uri ->
                    endpoint.equals(uri));
        };
    }

}