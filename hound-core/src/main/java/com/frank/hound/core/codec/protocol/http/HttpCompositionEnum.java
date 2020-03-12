package com.frank.hound.core.codec.protocol.http;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum HttpCompositionEnum {

    /**
     * url
     */
    URL("HTTP:URL"),
    /**
     * http头
     */
    HEADER("HTTP:HEADER"),
    /**
     * http请求体
     */
    BODY("HTTP:BODY");

    @Getter
    private String name;

}
