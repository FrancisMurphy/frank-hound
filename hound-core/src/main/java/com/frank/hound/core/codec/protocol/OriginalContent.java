package com.frank.hound.core.codec.protocol;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class OriginalContent {

    /**
     * 协议组成名
     */
    @Getter
    private final String name;

    /**
     * 未解析内容
     */
    @Getter
    private byte[] originalBody;
}
