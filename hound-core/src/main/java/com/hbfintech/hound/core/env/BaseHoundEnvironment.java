package com.hbfintech.hound.core.env;

import lombok.NonNull;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author frank
 */
public class BaseHoundEnvironment implements HoundConfigurableEnvironment
{
    private static final String DEFAULT_ENV_CONFIG_FILE_PATH = "HOUND-INK/hound.yml";

    private Map<String,String> activeProperties = new ConcurrentHashMap<>();

    private Map<String,String> properties = new ConcurrentHashMap<>();

    private Map<String,String> defalutProperties = new ConcurrentHashMap<>();

    public BaseHoundEnvironment()
    {
    }

    @Override
    public void setProperty(@NonNull String propertyKey, @NonNull String propertyValue)
    {
        properties.put(propertyKey,propertyValue);
    }

    @Override
    public void setDefalutProperty(String propertyKey,String defaultValue)
    {
        defalutProperties.put(propertyKey,defaultValue);
    }

    @Override
    public void refresh()
    {
        activeProperties.putAll(defalutProperties);
        activeProperties.putAll(properties);
    }

    @Override
    public String getDefalutProperty(String propertyKey)
    {
        return defalutProperties.get(propertyKey);
    }

    @Override
    public String getActiveProperty(String propertyKey)
    {
        return activeProperties.get(propertyKey);
    }
}
