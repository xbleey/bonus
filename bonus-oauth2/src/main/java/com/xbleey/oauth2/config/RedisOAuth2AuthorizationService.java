package com.xbleey.oauth2.config;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.util.Assert;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public class RedisOAuth2AuthorizationService implements OAuth2AuthorizationService {

    private static final String AUTHORIZATION_KEY_PREFIX = "oauth2:authorization:";
    private static final String TOKEN_KEY_PREFIX = "oauth2:token:";
    private static final long DEFAULT_TIMEOUT = 30; // 30分钟

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void save(OAuth2Authorization authorization) {
        Assert.notNull(authorization, "authorization cannot be null");

        String key = AUTHORIZATION_KEY_PREFIX + authorization.getId();

        // 保存授权信息
        redisTemplate.opsForValue().set(key, authorization, DEFAULT_TIMEOUT, TimeUnit.MINUTES);

        // 保存访问令牌索引
        OAuth2Authorization.Token<OAuth2AccessToken> accessToken = authorization.getAccessToken();
        if (accessToken != null && accessToken.getToken() != null) {
            String tokenKey = TOKEN_KEY_PREFIX + "access:" + accessToken.getToken().getTokenValue();
            Duration timeout = Duration.between(accessToken.getToken().getIssuedAt(), accessToken.getToken().getExpiresAt());
            redisTemplate.opsForValue().set(tokenKey, authorization.getId(), timeout.getSeconds(), TimeUnit.SECONDS);
        }

        // 保存刷新令牌索引
        OAuth2Authorization.Token<OAuth2RefreshToken> refreshToken = authorization.getRefreshToken();
        if (refreshToken != null && refreshToken.getToken() != null) {
            String tokenKey = TOKEN_KEY_PREFIX + "refresh:" + refreshToken.getToken().getTokenValue();
            redisTemplate.opsForValue().set(tokenKey, authorization.getId(), DEFAULT_TIMEOUT, TimeUnit.MINUTES);
        }

        // 保存state索引
        String state = authorization.getAttribute(OAuth2ParameterNames.STATE);
        if (state != null) {
            String stateKey = TOKEN_KEY_PREFIX + "state:" + state;
            redisTemplate.opsForValue().set(stateKey, authorization.getId(), 5, TimeUnit.MINUTES);
        }

        // 保存authorization_code索引
        String authorizationCode = authorization.getAttribute(OAuth2ParameterNames.CODE);
        if (authorizationCode != null) {
            String codeKey = TOKEN_KEY_PREFIX + "code:" + authorizationCode;
            redisTemplate.opsForValue().set(codeKey, authorization.getId(), 5, TimeUnit.MINUTES);
        }
    }

    @Override
    public void remove(OAuth2Authorization authorization) {
        Assert.notNull(authorization, "authorization cannot be null");

        String key = AUTHORIZATION_KEY_PREFIX + authorization.getId();
        redisTemplate.delete(key);

        // 删除访问令牌索引
        OAuth2Authorization.Token<OAuth2AccessToken> accessToken = authorization.getAccessToken();
        if (accessToken != null && accessToken.getToken() != null) {
            String tokenKey = TOKEN_KEY_PREFIX + "access:" + accessToken.getToken().getTokenValue();
            redisTemplate.delete(tokenKey);
        }

        // 删除刷新令牌索引
        OAuth2Authorization.Token<OAuth2RefreshToken> refreshToken = authorization.getRefreshToken();
        if (refreshToken != null && refreshToken.getToken() != null) {
            String tokenKey = TOKEN_KEY_PREFIX + "refresh:" + refreshToken.getToken().getTokenValue();
            redisTemplate.delete(tokenKey);
        }
    }

    @Override
    @Nullable
    public OAuth2Authorization findById(String id) {
        Assert.hasText(id, "id cannot be empty");
        String key = AUTHORIZATION_KEY_PREFIX + id;
        return (OAuth2Authorization) redisTemplate.opsForValue().get(key);
    }

    @Override
    @Nullable
    public OAuth2Authorization findByToken(String token, @Nullable OAuth2TokenType tokenType) {
        Assert.hasText(token, "token cannot be empty");

        String authorizationId = null;

        if (tokenType == null) {
            // 尝试所有类型
            authorizationId = findAuthorizationIdByToken(token, "access");
            if (authorizationId == null) {
                authorizationId = findAuthorizationIdByToken(token, "refresh");
            }
            if (authorizationId == null) {
                authorizationId = findAuthorizationIdByToken(token, "state");
            }
            if (authorizationId == null) {
                authorizationId = findAuthorizationIdByToken(token, "code");
            }
        } else if (OAuth2TokenType.ACCESS_TOKEN.equals(tokenType)) {
            authorizationId = findAuthorizationIdByToken(token, "access");
        } else if (OAuth2TokenType.REFRESH_TOKEN.equals(tokenType)) {
            authorizationId = findAuthorizationIdByToken(token, "refresh");
        } else if (OAuth2ParameterNames.STATE.equals(tokenType.getValue())) {
            authorizationId = findAuthorizationIdByToken(token, "state");
        } else if (OAuth2ParameterNames.CODE.equals(tokenType.getValue())) {
            authorizationId = findAuthorizationIdByToken(token, "code");
        }

        return authorizationId != null ? findById(authorizationId) : null;
    }

    @Nullable
    private String findAuthorizationIdByToken(String token, String type) {
        String tokenKey = TOKEN_KEY_PREFIX + type + ":" + token;
        return (String) redisTemplate.opsForValue().get(tokenKey);
    }
}
