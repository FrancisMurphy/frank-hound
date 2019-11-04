package com.hbfintech.hound.core.support;

import com.hbfintech.hound.core.acceptor.sorter.Sorter;

public interface HoundContext extends Sorter
{
    <T> T getComponent(String componentName, Class<T> componentClazz);
}
