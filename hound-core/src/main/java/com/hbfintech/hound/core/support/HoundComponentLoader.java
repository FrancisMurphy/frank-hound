package com.hbfintech.hound.core.support;

public interface HoundComponentLoader
{
    <T> T getComponent(String componentName, Class<T> componentClazz);
}
