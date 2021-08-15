package com.github.dreamteam.integration.misc;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
    }

    public static String asJsonString(final Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static  <T> T toObject(final String json, Class<T> result) {
        try {
            return objectMapper.readValue(json, result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
