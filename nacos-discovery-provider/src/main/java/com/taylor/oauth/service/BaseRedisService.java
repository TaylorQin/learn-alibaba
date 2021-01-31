package com.taylor.oauth.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class BaseRedisService {

    Map<String, String> persistedToken = new ConcurrentHashMap<>();

    public void delKey(String accessToken) {
        persistedToken.remove(accessToken);
    }

    public void setString(String accessToken, String appKey, long timeToken) {
        persistedToken.put(accessToken, appKey);
    }

    public String getString(String accessToken) {
        return persistedToken.get(accessToken);
    }
}
