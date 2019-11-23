package com.hbfintech.hound.core.common;

import com.hbfintech.hound.core.support.SorterRegistry;

/**
 * 责任链接口，用于隔离链处理与对应组件的业务实现
 * 通过装饰器模式获取责任链能力，e.g:{@link SorterRegistry}
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
