package com.hbfintech.hound.core.support;

/**
 * All managed by hound containers are hound beans, and you can listen for initialization through this interface.
 * @author frank
 */
public interface HoundEventListener
{

    void beforeInitialization(Object bean, String beanName);

    void afterInitialization(Object bean, String beanName);
}
