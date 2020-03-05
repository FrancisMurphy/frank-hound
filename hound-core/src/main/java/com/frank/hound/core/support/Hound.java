package com.frank.hound.core.support;

import com.frank.hound.core.env.HoundEnvironment;
import lombok.NonNull;

/**
 * Hound is an interface that provides
 * the ability to initialize the portal's ioc
 * and register corresponding hound beans
 * {@link com.frank.hound.core.support.HoundSheep}
 *
 * @author frank
 */
public interface Hound
{
    /**
     * Get hound sheep that is base scalable functional nodes.
     * @param sheepName hound sheep name
     * @param sheepClazz hound sheep clazz
     * @param <T> hound sheep clazz
     * @return return hound sheep
     */
    <T> T getSheep(@NonNull String sheepName, @NonNull Class<T> sheepClazz);

    /**
     * Get hound bridge bean which hold the third party extension interface call.
     * @param bridgeName
     * @return
     */
    Object getBridge(@NonNull String bridgeName);

    /**
     * Publish hound shepherd event.
     * @param event
     */
    void publishEvent(@NonNull EventObject event);

    /**
     * Trigger sorting operation
     */
    void sort();

    HoundEnvironment getEnvironment();
}
