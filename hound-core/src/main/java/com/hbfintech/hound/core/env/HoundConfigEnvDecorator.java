package com.hbfintech.hound.core.env;

/**
 * The decorator for {@link HoundConfigurableEnvironment}
 * @author frank
 */
public class HoundConfigEnvDecorator implements HoundConfigurableEnvironment
{
    private HoundConfigurableEnvironment houndEnv;

    public HoundConfigEnvDecorator(
            HoundConfigurableEnvironment houndEnv)
    {
        this.houndEnv = houndEnv;
    }

    @Override
    public void setProperty(String propertyKey, String propertyValue)
    {
        houndEnv.setProperty(propertyKey,propertyValue);
    }

    @Override
    public void setDefalutProperty(String propertyKey, String defaultValue)
    {
        houndEnv.setDefalutProperty(propertyKey,defaultValue);
    }

    @Override
    public void refresh()
    {
        houndEnv.refresh();
    }


    @Override
    public String getDefalutProperty(String propertyKey)
    {
        return houndEnv.getDefalutProperty(propertyKey);
    }

    @Override
    public String getActiveProperty(String propertyKey)
    {
        return houndEnv.getActiveProperty(propertyKey);
    }
}
