package com.hbfintech.hound.core.support;

/**
 * 责任链接口，用于隔离链处理与对应组件的业务实现
 * @param <T>
 */
public interface Chain<T>
{
    /**
     * set next gay
     * @param next
     */
    void setNext(T next);
}
