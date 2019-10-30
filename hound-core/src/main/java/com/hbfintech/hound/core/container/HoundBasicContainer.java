package com.hbfintech.hound.core.container;

import java.util.HashMap;
import java.util.Map;

public class HoundBasicContainer<T>
{
    private Map<String,T> componentInstanceMapper = new HashMap<>();

    public HoundBasicContainer()
    {
    }

    public void add(String componentName, T componentInstance)
    {
        componentInstanceMapper.put(componentName,componentInstance);
    }

    public T get(String componentName)
    {
        return componentInstanceMapper.get(componentName);
    }
}
