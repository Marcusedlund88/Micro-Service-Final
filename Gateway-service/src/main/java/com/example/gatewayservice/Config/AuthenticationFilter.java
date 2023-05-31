package com.example.gatewayservice.Config;

import com.example.gatewayservice.Util.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);
    @Autowired
    private RouteValidator routeValidator;

    @Autowired
    private JwtService jwtService;


    public AuthenticationFilter(WebClient.Builder webclientBuilder) {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {

        return ((exchange, chain)->{
            log.info(routeValidator.openEndpoints.toString());
            if(routeValidator.isSecure().test(exchange.getRequest())){
                if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("No token");
                }

                String header = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                log.info(header);
                if(header != null && header.startsWith("Bearer ")){
                    header = header.substring(7);
                }
                log.info(header);
                try{
                    jwtService.validateToken(header);
                }
                catch (Exception e){
                    throw new RuntimeException("Not valid");
                };
            }

            return chain.filter(exchange);
        });
    }

    public static class Config{

    }
}