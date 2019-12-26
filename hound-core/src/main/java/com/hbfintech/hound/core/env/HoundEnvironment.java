package com.hbfintech.hound.core.env;

/**
 *
 * @author frank
 */
public interface HoundEnvironment
{
    /**
     * @param propertyKey
     * @return
     */
    String getDefalutProperty(String propertyKey);

    /**
     * @param propertyKey
     * @return
     */
    String getActiveProperty(String propertyKey);
}
