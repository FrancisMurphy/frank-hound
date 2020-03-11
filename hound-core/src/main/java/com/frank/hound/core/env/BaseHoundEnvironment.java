package com.frank.hound.core.env;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author frank
 */
@Slf4j
public class BaseHoundEnvironment implements HoundConfigurableEnvironment
{
    private Map<String,String> activeProperties = new ConcurrentHashMap<>();

    private Map<String,String> properties = new ConcurrentHashMap<>();

    private Map<String,String> defalutProperties = new ConcurrentHashMap<>();

    private AtomicBoolean isInitialized = new AtomicBoolean(false);

    public BaseHoundEnvironment()
    {
    }

    @Override
    public void setProperty(@NonNull String propertyKey, @NonNull String propertyValue)
    {
        properties.put(propertyKey,propertyValue);
    }

    @Override
    public void setDefaultProperty(@NonNull String propertyKey,@NonNull String defaultValue)
    {
        defalutProperties.put(propertyKey,defaultValue);
    }

    @Override
    public void refresh()
    {
        //Refresh properties
        activeProperties.putAll(defalutProperties);
        activeProperties.putAll(properties);
    }


    @Override
    public String getDefalutProperty(@NonNull String propertyKey)
    {
        return defalutProperties.get(propertyKey);
    }

    @Override
    public String getActiveProperty(@NonNull String propertyKey)
    {
        return activeProperties.get(propertyKey);
    }

}
