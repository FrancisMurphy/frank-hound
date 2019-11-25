package com.hbfintech.hound.core.env;

import java.util.HashSet;
import java.util.Set;

/**
 * @author frank
 */
public class HoundValidKeyEnvironment extends HoundConfigEnvDecorator
{
    private Set<String> validKeys = new HashSet<>();

    public HoundValidKeyEnvironment(
            HoundConfigurableEnvironment houndEnv)
    {
        super(houndEnv);
    }

    @Override
    public void refresh()
    {

        super.refresh();
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
