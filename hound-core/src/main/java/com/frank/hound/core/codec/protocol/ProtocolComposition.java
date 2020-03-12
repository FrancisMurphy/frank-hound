package com.frank.hound.core.codec.protocol;

import lombok.Data;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * 协议组成描述，用于持有协议属性信息，比如HTTP的Header、Body等
 * @author frank
 */
@Data
public class ProtocolComposition {

    public ProtocolComposition(String name) {
        this.name = name;
    }

    /**
     * 协议组成名
     */
    private final String name;

    /**
     * 是否被解析
     */
    private boolean isParsed;

    /**
     * 解析后的map
     */
    private final Map<String,String> elements = new HashMap<>();

    /**
     * 未解析内容
     */
    private byte[] originalBody;

    public void addElement(@NonNull String k, @NonNull String v) {
        elements.put(k,v);
    }
}
