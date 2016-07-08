package com.automationrhapsody.reststub.persistence;

import java.util.HashMap;
import java.util.Map;

public class AuthDB {
    private static final Map<String, String> AUTH = new HashMap<>();

    static {
        AUTH.put("adminApiKey", "admSecretKey");
        AUTH.put("userApiKey", "usrSecretKey");
    }

    public static String getSecretKey(String apiKey) {
        return AUTH.get(apiKey);
    }
}
