package com.hbfintech.hound.core.env;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface HoundConfigurableEnvironment extends HoundEnvironment
{
    private Map<String,String> properties = new ConcurrentHashMap<>();

    public void setProperty(String propertyKey,String propertyValue)
    {
        properties.put(propertyKey,propertyValue);
    }

    @Override
    public String getPropery(String propertyKey)
    {
        return properties.get(propertyKey);
    }
}
