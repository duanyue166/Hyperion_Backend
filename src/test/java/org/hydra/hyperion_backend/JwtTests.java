package org.hydra.hyperion_backend;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.hydra.hyperion_backend.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
public class JwtTests {
    private static String token;

    @BeforeEach
    public void testGen() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", "dy233");
        claims.put("password", "123456");
        token = JwtUtil.genToken(claims);
        System.out.println(token);
    }

    @Test
    public void testParse() {
        Map<String, Object> claims = JwtUtil.parseToken(token);
        System.out.println(claims);
    }

    @Test
    public void testExpirationTime() {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256("dut233"))
                .build()
                .verify(token);
        Date expirationTime = decodedJWT.getExpiresAt();
        System.out.println("Expiration Time: " + expirationTime);
    }
}
