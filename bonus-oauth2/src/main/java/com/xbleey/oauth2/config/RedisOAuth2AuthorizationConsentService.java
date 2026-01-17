package com.xbleey.oauth2.config;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsent;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.util.Assert;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public class RedisOAuth2AuthorizationConsentService implements OAuth2AuthorizationConsentService {

    private static final String CONSENT_KEY_PREFIX = "oauth2:consent:";
    private static final long DEFAULT_TIMEOUT = 30; // 30天

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void save(OAuth2AuthorizationConsent authorizationConsent) {
        Assert.notNull(authorizationConsent, "authorizationConsent cannot be null");

        String key = buildKey(authorizationConsent.getRegisteredClientId(), authorizationConsent.getPrincipalName());
        redisTemplate.opsForValue().set(key, authorizationConsent, DEFAULT_TIMEOUT, TimeUnit.DAYS);
    }

    @Override
    public void remove(OAuth2AuthorizationConsent authorizationConsent) {
        Assert.notNull(authorizationConsent, "authorizationConsent cannot be null");

        String key = buildKey(authorizationConsent.getRegisteredClientId(), authorizationConsent.getPrincipalName());
        redisTemplate.delete(key);
    }

    @Override
    @Nullable
    public OAuth2AuthorizationConsent findById(String registeredClientId, String principalName) {
        Assert.hasText(registeredClientId, "registeredClientId cannot be empty");
        Assert.hasText(principalName, "principalName cannot be empty");

        String key = buildKey(registeredClientId, principalName);
        return (OAuth2AuthorizationConsent) redisTemplate.opsForValue().get(key);
    }

    private String buildKey(String registeredClientId, String principalName) {
        return CONSENT_KEY_PREFIX + registeredClientId + ":" + principalName;
    }
}
