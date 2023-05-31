package com.example.gatewayservice.Util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@Service
public class JwtService {


    //@Value("${spring.app.SECRET_KEY}")
    //private String SECRET_KEY;
    private String SECRET_KEY = "432A462D4A614E645267556B586E3272357538782F413F4428472B4B62506553";

    public void validateToken(final String token){
        Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token);
    }
    private Key getSignInKey() {
        byte [] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);

    }
}