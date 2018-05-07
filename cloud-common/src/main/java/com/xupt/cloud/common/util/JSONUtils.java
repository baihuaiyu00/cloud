package com.xupt.cloud.common.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;

/**
 * JSON转换工具
 * @author zhangkaitao
 *
 */
public class JSONUtils {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final ObjectMapper PRETTY_OBJECT_MAPPER = new ObjectMapper();
    private static final String JSON_CHARSET = "UTF-8";

    static {
        OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        OBJECT_MAPPER.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        PRETTY_OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        PRETTY_OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        PRETTY_OBJECT_MAPPER.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
    }

    public JSONUtils() {
    }

    public static String toJsonOrJSonP(Object obj, String callback) {

        String json = null;
        if (StringUtils.isNotBlank(callback)) {
            json = JSONUtils.toJsonP(callback, obj);
        } else {
            json = JSONUtils.toJSON(obj);
        }
        return json;
    }


    public static String toJSON(Object obj) {
        return toJSON(obj, false);
    }

    public static String toJSON(Object obj, boolean pretty) {
        try {
            if (obj == null) {
                return null;
            }
            if (pretty) {
                return new String(OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsBytes(obj), JSON_CHARSET);
            } else {
                return new String(OBJECT_MAPPER.writeValueAsBytes(obj), JSON_CHARSET);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] toJSONAsBytes(Object obj) throws Exception {
        if (obj == null) {
            return null;
        }
        return toJSON(obj).getBytes(JSON_CHARSET);
    }


    public static <T> T fromJson(byte[] data, Class<T> javaType) throws Exception {
        if (data == null) {
            return null;
        }
        return fromJson(new String(data, JSON_CHARSET), javaType);
    }


    public static <T> T fromJson(String jsonAsString, Class<T> valueType) {
        if (org.apache.commons.lang.StringUtils.isBlank(jsonAsString)) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readValue(jsonAsString, valueType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T fromJson(String jsonAsString, JavaType javaType) {
        if (org.apache.commons.lang.StringUtils.isBlank(jsonAsString)) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readValue(jsonAsString, javaType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T fromJson(String jsonAsString, TypeReference<T> valueTypeRef){
        if(org.apache.commons.lang.StringUtils.isBlank(jsonAsString)) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readValue(jsonAsString, valueTypeRef);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String toJsonP(String functionName, Object object) {
        try {
            return toJSON(new JSONPObject(functionName, object));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}