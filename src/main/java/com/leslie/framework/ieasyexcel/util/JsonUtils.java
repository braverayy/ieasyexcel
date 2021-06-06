package com.leslie.framework.ieasyexcel.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * @author leslie
 * @date 2021/6/6
 */
@Slf4j
public class JsonUtils {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String toJsonString(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("json序列化出错：{}", obj, e);
            return null;
        }
    }

    public static <T> T toObject(String json, Class<T> clazz) {
        try {
            return json != null ? OBJECT_MAPPER.readValue(json, clazz) : null;
        } catch (IOException e) {
            log.error("json解析出错：{}", json, e);
            return null;
        }
    }

    public static <T> T toObject(String json, TypeReference<T> type) {
        try {
            return json != null ? OBJECT_MAPPER.readValue(json, type) : null;
        } catch (IOException e) {
            e.printStackTrace();
            log.error("json解析出错：{}", json, e);
            return null;
        }
    }

    public static <E> List<E> toList(String json, Class<E> eClass) {
        try {
            return json != null ? OBJECT_MAPPER.readValue(json, OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, eClass)) : Collections.emptyList();
        } catch (IOException e) {
            log.error("json解析出错：{}", json, e);
            return null;
        }
    }
}
