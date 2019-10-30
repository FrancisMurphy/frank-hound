package com.hbfintech.hound.core.context;

public interface HoundComponentContext
{
    <T> T getComponent(String componentName, Class<T> componentClazz);
}
