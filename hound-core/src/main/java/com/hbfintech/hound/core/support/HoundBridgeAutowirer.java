package com.hbfintech.hound.core.support;

import lombok.NonNull;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;

/**
 * Hound bridge 自动注入器
 * @author frank
 */
public class HoundBridgeAutowirer
{
    private HoundBridgeRegistry houndBridgeRegistry;

    public HoundBridgeAutowirer(
            @NonNull HoundBridgeRegistry houndBridgeRegistry)
    {
        this.houndBridgeRegistry = houndBridgeRegistry;
    }

    public void init()
    {
        //遍历bridge
        if (houndBridgeRegistry == null)
        {
            throw new HoundException(
                    "Can not find houndBridgeRegistry! Hound init fail!");
        }

        Iterator<Map.Entry<String, Object>> bridgeIterator = houndBridgeRegistry
                .getBridges();
        while (bridgeIterator.hasNext())
        {
            Map.Entry<String, Object> bridgeItem = bridgeIterator.next();
            final String bridgeName = bridgeItem.getKey();
            final Object bridgeObj = bridgeItem.getValue();
            final Class bridgeClazz = bridgeObj.getClass();

            //获取声明了{@link HoundAutowired}的成员
            for (Field field : bridgeClazz.getDeclaredFields())
            {
                if (!field.isAnnotationPresent(HoundAutowired.class))
                {
                    continue;
                }

                boolean fieldNotAccessibleFlag = false;
                if (!field.isAccessible())
                {
                    fieldNotAccessibleFlag = true;
                    field.setAccessible(true);

                    final Class componentInstanceClazz = field.getType();
                    final Class componentBaseClazz = HoundClazzLibrary
                            .getHoundComponentClazz(componentInstanceClazz);
                    if (componentBaseClazz != null)
                    {
                        //Get and inject component instances
                        Object componentInstance = Sheepehound.getContext()
                                .getSheep(bridgeName, componentBaseClazz);

                        try
                        {
                            field.set(bridgeObj,componentInstance);
                        }
                        catch (IllegalAccessException e)
                        {
                           //do nothing
                        }
                    }
                }

                if (fieldNotAccessibleFlag)
                {
                    field.setAccessible(false);
                }
            }
        }
    }
}
