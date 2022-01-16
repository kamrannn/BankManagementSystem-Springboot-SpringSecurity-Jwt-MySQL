package com.app.BankSystem.Util;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.HashMap;

@Component
public class HashMapResponse {

    public static HashMap<String, Object> getResponse(String message, Object obj) throws ParseException {
        HashMap<String, Object> map = new HashMap<>();
        if (obj == null) {
            map.put("message", message);
            map.put("Timestamp", LocalDateTime.now());
        } else {
            map.put("message", message);
            map.put("Timestamp", LocalDateTime.now());
            map.put("result", obj);
        }
        return map;
    }
}
