package com.frank.hound.core.env;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author frank
 */
public class HoundValidKeyEnvironment extends HoundConfigEnvDecorator
{
    private Set<String> validKeys = new HashSet<>();

    private static final String VALID_KEY_CONFIG_KEY = "config.valid.keys";

    private AtomicBoolean isInitialized = new AtomicBoolean(false);

    public HoundValidKeyEnvironment(
            HoundConfigurableEnvironment houndEnv)
    {
        super(houndEnv);
    }

    @Override
    public void refresh()
    {
        super.refresh();

        if(!isInitialized.get())
        {
            getDefalutProperty(VALID_KEY_CONFIG_KEY);


        }
    }

    @Override
    public void setDefalutProperty(String propertyKey, String defaultValue)
    {
        if(validKeys.contains(propertyKey))
        {
            super.setDefalutProperty(propertyKey, defaultValue);
        }
    }

    @Override
    public void setProperty(String propertyKey, String propertyValue)
    {
        if(validKeys.contains(propertyKey))
        {
            super.setProperty(propertyKey, propertyValue);
        }
    }
}
