package com.github.dreamteam.integration.misc;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.web.PageableArgumentResolver;
import org.springframework.data.web.PagedResourcesAssembler;

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

    public static <T> T toObject(final String json, Class<T> result) {
        try {
            return objectMapper.readValue(json, result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> Page<T> toPage(final String json, Class<T> result) {
        JavaType javaType = objectMapper.getTypeFactory()
                .constructParametricType(RestResponsePage.class, result);
        try {
            return objectMapper.readValue(json, javaType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
