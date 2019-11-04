package com.hbfintech.hound.core.support;

import lombok.NonNull;

public interface HoundContext
{
    <T> T getComponent(@NonNull String componentName, @NonNull Class<T> componentClazz);
}
