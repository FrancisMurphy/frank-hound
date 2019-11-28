package com.hbfintech.hound.core.env;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author frank
 */
public interface HoundConfigurableEnvironment extends HoundEnvironment
{
    /**
     *
     * @param propertyKey
     * @param propertyValue
     */
    void setProperty(String propertyKey,String propertyValue);

    /**
     *
     * @param propertyKey
     * @param defaultValue
     */
    void setDefalutProperty(String propertyKey,String defaultValue);

    /**
     *
     * Refresh environment of hound
     */
    void refresh();
}
