package com.frank.hound.core.env;

/**
 * The decorator for {@link HoundConfigurableEnvironment}
 * @author frank
 */
public abstract class HoundEnvironmentDecorator implements HoundConfigurableEnvironment
{
    private HoundConfigurableEnvironment houndEnv;

    public HoundEnvironmentDecorator(
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
    public void setDefaultProperty(String propertyKey, String defaultValue)
    {
        houndEnv.setDefaultProperty(propertyKey,defaultValue);
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
