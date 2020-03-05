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
    private static final String DEFAULT_ENV_CONFIG_FILE_PATH = "/HOUND-INF/hound.properties";

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
    public void setDefalutProperty(@NonNull String propertyKey,@NonNull String defaultValue)
    {
        defalutProperties.put(propertyKey,defaultValue);
    }

    @Override
    public void refresh()
    {
        //Initial read configuration file
        if(!isInitialized.get())
        {
            if (initDefaultConfig())
            {
                return;
            }

            isInitialized.set(true);
        }

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

    private boolean initDefaultConfig()
    {
        Properties properties = new Properties();
        InputStream reader = getClass().getResourceAsStream(DEFAULT_ENV_CONFIG_FILE_PATH);
        try
        {
            properties.load(reader);
        }
        catch (IOException e)
        {
            //do nothing
            log.error("Hound env load properties fail, error:",e);
            return true;
        }

        Set<String> innerPropertyKeysSet = properties.stringPropertyNames();

        for(String innerPropertyKey:innerPropertyKeysSet)
        {
            defalutProperties.put(innerPropertyKey,properties.getProperty(innerPropertyKey));
        }
        return false;
    }

}
