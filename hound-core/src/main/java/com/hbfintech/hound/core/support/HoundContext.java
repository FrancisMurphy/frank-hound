package com.hbfintech.hound.core.support;

import lombok.NonNull;

/**
 *
 * @author frank
 */
public interface HoundContext
{
    /**
     * Get hound component bean that is base scalable functional nodes.
     * @param componentName hound component bean name
     * @param componentClazz hound component bean clazz
     * @param <T> hound component bean clazz
     * @return return hound component bean
     */
    <T> T getComponent(@NonNull String componentName, @NonNull Class<T> componentClazz);

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
     *
     */
    void sort();
}
