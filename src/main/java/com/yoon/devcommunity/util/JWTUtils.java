package com.yoon.devcommunity.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.yoon.devcommunity.exception.CustomException;
import com.yoon.devcommunity.exception.ErrorCode;
import com.yoon.devcommunity.model.TokenInfo;

import java.nio.charset.StandardCharsets;
import java.util.Date;

public abstract class JWTUtils {

    private static final String KEY = "secretKey";
    private static final long TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7; // 1 week
    private static final Algorithm ALGORITHM = Algorithm.HMAC512(KEY.getBytes(StandardCharsets.UTF_8));

    public static String createToken(Long id, String email) {

        final Date now = new Date();

        return JWT.create()
                .withExpiresAt(new Date(now.getTime() + TOKEN_EXPIRE_TIME))
                .withClaim("id", id)
                .withSubject(email)
                .sign(ALGORITHM);
    }

    public static TokenInfo getTokenInfo(String token) {
        DecodedJWT jwt;

        try {
            jwt = JWT.require(ALGORITHM).build().verify(token);
        } catch (RuntimeException e) {
            throw new CustomException(ErrorCode.REQUIRED_LOGIN_AGAIN);
        }

        Long id = jwt.getClaim("id").asLong();
        String email = jwt.getSubject();

        return new TokenInfo(id, email);
    }
}