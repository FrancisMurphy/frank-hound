package com.hbfintech.hound.core.acceptor.sorter;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.hbfintech.hound.core.context.TraceContext;
import com.hbfintech.hound.core.support.Chain;
import com.hbfintech.hound.core.support.TraceContextThreadLocalKeeper;
import lombok.Setter;

/**
 * Chain of responsibility
 * BaseSorter 实现了分拣员的持有获取上下文对象、基本分拣流程、自身的链式处理，
 * 分拣员的基本分拣流程实现通过实现{@link com.hbfintech.hound.core.acceptor.sorter}
 * 责任链实现通过{@link com.hbfintech.hound.core.support.Chain}
 * 分拣的具体业务实现通过子类实现
 * @author frank
 */
public abstract class BaseSorter implements Sorter, Chain<Sorter>
{
    private Sorter nextSorter;

    private TransmittableThreadLocal<TraceContext> traceContextThreadLocal = TraceContextThreadLocalKeeper.TRACE_TRACELOCAL_CONTEXT;

    public BaseSorter()
    {
    }

    @Override
    public void sort()
    {
        try
        {
            sorting(traceContextThreadLocal);
        }
        catch (Exception e)
        {
            //TODO：缺少专用异常体系
        }
        finally
        {
            if(nextSorter!=null)
            {
                nextSorter.sort();
            }
        }
    }

    /**
     * @param traceContextThreadLocal
     */
    protected abstract void sorting(TransmittableThreadLocal<TraceContext> traceContextThreadLocal);

    @Override
    public void setNext(
            Sorter nextSorter)
    {
        this.nextSorter = nextSorter;
    }
}
