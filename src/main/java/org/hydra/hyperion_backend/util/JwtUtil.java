package org.hydra.hyperion_backend.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.hydra.hyperion_backend.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {
    private static String KEY;
    private static long EXPIRED_TIME;

    @Autowired
    JwtUtil(AppConfig appConfig) {
        KEY = appConfig.getJwt().getKey();
        EXPIRED_TIME = Duration.parse("PT" + appConfig.getJwt().getExpiredTime().toUpperCase()).toMillis();
    }

    //接收业务数据,生成token并返回
    public static String genToken(Map<String, Object> claims) {

        return JWT.create()
                .withClaim("claims", claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRED_TIME))
                .sign(Algorithm.HMAC256(KEY));
    }

    //接收token,验证token,并返回业务数据
    public static Map<String, Object> parseToken(String token) {
        return JWT.require(Algorithm.HMAC256(KEY))
                .build()
                .verify(token)
                .getClaim("claims")
                .asMap();
    }
}
