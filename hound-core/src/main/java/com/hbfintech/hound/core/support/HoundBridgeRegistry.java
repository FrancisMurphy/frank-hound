package com.hbfintech.hound.core.support;

import lombok.NonNull;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HoundBridgeRegistry
{
    /**
     * hound bridge mapper
     * {@link com.hbfintech.hound.core.support.HoundBridge}
     */
    private Map<String, Object> bridgeMapper = new HashMap<>();

    public HoundBridgeRegistry()
    {
    }

    public void init()
    {
        initBriage();
    }

    private void initBriage()
    {
        bridgeMapper = getTargetHoundBridge();
    }

    private Map<String, Object> getTargetHoundBridge()
    {
        return scanSpecifyPkgBridge("com.hbfintech.hound");
    }

    private Map<String, Object> scanSpecifyPkgBridge(
            @NonNull String pkgPrefix)
    {
        Reflections reflections = new Reflections(pkgPrefix);

        Set<Class<?>> targetBridgeClazzSet = reflections.getTypesAnnotatedWith(
                HoundBridge.class);
        Map<String, Object> targetBridgeClazzMap = new HashMap<>(targetBridgeClazzSet.size());
        for(Class targetComponentClazz:targetBridgeClazzSet)
        {
            try
            {
                //TODO:将实例化移入Instance Factory
                targetBridgeClazzMap.put(((HoundBridge)targetComponentClazz.getAnnotation(HoundBridge.class)).value(),targetComponentClazz.newInstance());
            }
            catch (InstantiationException | IllegalAccessException e)
            {
                //do nothing
            }
        }
        return targetBridgeClazzMap;
    }

    public Iterator<Map.Entry<String, Object>> getBridges()
    {
        return bridgeMapper.entrySet().iterator();
    }

    public Object getBridge(@NonNull String bridgeName)
    {
        return bridgeMapper.get(bridgeName);
    }
}