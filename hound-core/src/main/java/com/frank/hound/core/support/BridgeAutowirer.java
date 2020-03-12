package com.frank.hound.core.support;

import lombok.NonNull;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;

/**
 * Hound bridge automatic injector
 *
 * @author frank
 */
public class BridgeAutowirer {
    public void init(@NonNull BridgeRegistry houndBridgeRegistry) {
        // Traversing bridge
        if (houndBridgeRegistry == null) {
            throw new HoundInitException(getClass(),"Can not find houndBridgeRegistry! Hound init fail!");
        }

        Iterator<Map.Entry<String, Object>> bridgeIterator = houndBridgeRegistry.getBridges();
        while (bridgeIterator.hasNext()) {
            Map.Entry<String, Object> bridgeItem = bridgeIterator.next();
            final String bridgeName = bridgeItem.getKey();
            final Object bridgeObj = bridgeItem.getValue();
            final Class bridgeClazz = bridgeObj.getClass();

            // Got field which statement {@link HoundAutowired}
            for (Field field : bridgeClazz.getDeclaredFields()) {
                if (!field.isAnnotationPresent(HoundAutowired.class)) {
                    continue;
                }

                boolean fieldNotAccessibleFlag = false;
                if (!field.isAccessible()) {
                    fieldNotAccessibleFlag = true;
                    field.setAccessible(true);

                    final Class componentInstanceClazz = field.getType();
                    final Class componentBaseClazz = HoundClazzLibrary.getHoundComponentClazz(componentInstanceClazz);
                    if (componentBaseClazz != null) {
                        // Get and inject component instances
                        Object componentInstance = Sheepehound.getHound().getSheep(bridgeName, componentBaseClazz);

                        try {
                            field.set(bridgeObj, componentInstance);
                        } catch (IllegalAccessException e) {
                            // do nothing
                        }
                    }
                }

                if (fieldNotAccessibleFlag) {
                    field.setAccessible(false);
                }
            }
        }
    }
}
