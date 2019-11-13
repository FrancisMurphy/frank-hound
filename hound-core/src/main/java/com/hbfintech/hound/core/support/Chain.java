package com.hbfintech.hound.core.support;

/**
 * 责任链接口，用于隔离链处理与对应组件的业务实现
 * 通过装饰器模式获取责任链能力，e.g:{@link com.hbfintech.hound.core.acceptor.sorter.SorterInitializer}
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
