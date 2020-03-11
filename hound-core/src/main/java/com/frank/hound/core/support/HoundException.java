package com.frank.hound.core.support;

import lombok.Getter;
import lombok.NonNull;

/**
 * Hound Exception
 * @author frank
 */
public class HoundException extends RuntimeException
{
    /**
     * 错误信息
     */
    @Getter
    private final String message;

    /**
     * 来源类
     */
    @Getter
    private final Class<?> sourceClazz;

    /**
     * 原始堆栈
     */
    @Getter
    private Throwable originalStack;

    public HoundException(@NonNull Class<?> sourceClazz, @NonNull String message)
    {
        super(message);
        this.message = "#Frank-Hound# "+message;
        this.sourceClazz = sourceClazz;
    }

    public HoundException(@NonNull Class<?> sourceClazz, @NonNull String message, @NonNull Throwable originalStack)
    {
        super(message);
        this.message = message;
        this.sourceClazz = sourceClazz;
        this.originalStack = originalStack;
    }
}
