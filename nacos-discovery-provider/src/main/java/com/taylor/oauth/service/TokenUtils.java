package com.taylor.oauth.service;

import java.util.UUID;

public class TokenUtils {
    public static String getAccessToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
