package com.okayhu.framework.ieasyexcel.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author leslie
 * @date 2021/6/6
 */
@Slf4j
public class JsonUtils {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String toJsonString(Object obj) {
        try {
            return obj == null ? null : OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("Unable to convert object to json string", e);
            return null;
        }
    }

    public static <T> T toObject(String jsonString, Class<T> clazz) {
        if (clazz == null) throw new IllegalArgumentException("Clazz must not be null");

        try {
            return jsonString == null ? null : OBJECT_MAPPER.readValue(jsonString, clazz);
        } catch (IOException e) {
            log.error("Unable to convert json string [{}] to [{}] type object", jsonString, clazz.getName(), e);
            return null;
        }
    }

    public static <T> List<T> toList(String jsonString, Class<T> clazz) {
        if (clazz == null) throw new IllegalArgumentException("Clazz must not be null");

        if (jsonString == null) {
            return Collections.emptyList();
        }
        CollectionType listType = OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, clazz);
        return toObject(jsonString, listType);
    }

    public static <K, V> Map<K, V> toMap(String jsonString, Class<K> keyClazz, Class<V> valueClazz) {
        if (keyClazz == null || valueClazz == null)
            throw new IllegalArgumentException("KeyClazz and valueClazz must not be null");

        if (jsonString == null) {
            return Collections.emptyMap();
        }
        MapType mapType = OBJECT_MAPPER.getTypeFactory().constructMapType(Map.class, keyClazz, valueClazz);
        return toObject(jsonString, mapType);
    }

    public static <T> T toObject(String jsonString, JavaType type) {
        if (type == null) throw new IllegalArgumentException("JavaType must not be null");

        try {
            return jsonString == null ? null : OBJECT_MAPPER.readValue(jsonString, type);
        } catch (IOException e) {
            log.error("Unable to convert json string [{}] to [{}] type object", jsonString, type.getTypeName(), e);
            return null;
        }
    }

    public static <T> T toObject(String jsonString, TypeReference<T> typeReference) {
        if (typeReference == null) throw new IllegalArgumentException("TypeReference must not be null");

        try {
            return jsonString == null ? null : OBJECT_MAPPER.readValue(jsonString, typeReference);
        } catch (IOException e) {
            log.error("Unable to convert json string [{}] to [{}] type object", jsonString, typeReference.getType().getTypeName(), e);
            return null;
        }
    }
}
