package com.frank.hound.core.requester.postman;

/**
 * @author frank
 */
public interface Postman
{
    /**
     * @param postParams 待推送的参数
     */
    void post(Object... postParams);
}
