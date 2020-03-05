package com.frank.hound.core.common;

import com.frank.hound.core.support.SorterRegistry;

/**
 * Responsibility chain interface for isolating chain processing
 * from business implementations of corresponding components
 * Obtaining responsibility chain capability through decorator mode，
 * e.g:{@link SorterRegistry}
 *
 * @author frank
 * @param <T> Chain of responsibility instance type
 */
public interface Chain<T>
{
    /**
     * set next gay
     * @param next
     */
    void setNext(T next);
}
