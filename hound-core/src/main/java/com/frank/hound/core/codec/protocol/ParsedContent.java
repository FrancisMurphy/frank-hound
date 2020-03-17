package com.frank.hound.core.codec.protocol;

import com.frank.hound.core.context.ContextElement;
import lombok.Data;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * 协议组成描述，用于持有协议属性信息，比如HTTP的Header、Body等
 * @author frank
 */
@Data
public class ParsedContent {

    public ParsedContent() {
    }

    /**
     * 是否被解析完毕
     */
    private boolean isParseCompleted;

    /**
     * 解析后的map
     */
    private final Map<String, ContextElement<?>> elements = new HashMap<>();

    public void addElement(@NonNull String k, @NonNull ContextElement<?> v) {
        elements.put(k,v);
    }
}
