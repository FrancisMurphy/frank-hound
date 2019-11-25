package com.hbfintech.hound.core.env;

import java.util.Set;

/**
 *
 * @author frank
 */
public interface HoundEnvironment
{
    /**
     * @param propertyKey
     * @return
     */
    String getDefalutProperty(String propertyKey);

    /**
     * @param propertyKey
     * @return
     */
    String getActiveProperty(String propertyKey);
}
