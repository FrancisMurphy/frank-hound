package com.frank.hound.core.codec;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.frank.hound.core.support.HoundSheep;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;

/**
 * Json 编码器,使用Jackson
 *
 * @author frank
 */
@Slf4j
@HoundSheep("json")
public class JsonCodec implements Codec{

    private ObjectMapper objectMapper;

    public JsonCodec() {
        init();
    }

    private void init() {
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public String build(@NonNull Object targetObj) {
        try {
            return objectMapper.writeValueAsString(targetObj);
        } catch (JsonProcessingException e) {
            log.error("Can not codec clazz:"+targetObj.getClass()+"to String, Exception:",e);
            return null;
        }
    }

    @Override
    public <T> T parseFrom(@NonNull String source, @NonNull Class<T> targetClazz) {
        try {
            return objectMapper.readValue(source,targetClazz);
        } catch (JsonProcessingException e) {
            log.error("Can not codec clazz:"+targetClazz,e);
            return null;
        }
    }
}
