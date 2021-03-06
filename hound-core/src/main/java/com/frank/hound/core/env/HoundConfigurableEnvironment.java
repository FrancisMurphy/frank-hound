package com.frank.hound.core.env;

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
    void setDefaultProperty(String propertyKey,String defaultValue);

    /**
     * Refresh environment of hound
     */
    void refresh();
}
