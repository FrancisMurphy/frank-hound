package com.hbfintech.hound.core.acceptor.sorter;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.hbfintech.hound.core.context.TraceContext;
import com.hbfintech.hound.core.keeper.TraceContextThreadLocalKeeper;
import lombok.Setter;

/**
 * Chain of responsibility
 * @author frank
 */
public abstract class BaseSorter implements Sorter
{
    @Setter
    private BaseSorter nextSorter;

    TransmittableThreadLocal<TraceContext> traceContextThreadLocal = TraceContextThreadLocalKeeper.TRACE_TRACELOCAL_CONTEXT;

    public BaseSorter()
    {
    }

    @Override
    public void sort()
    {
        try
        {
            sorting(traceContextThreadLocal);
            if(nextSorter!=null)
            {
                nextSorter.sort();
            }
        }
        catch (Exception e)
        {
            //TODO：缺少专用异常体系
        }

    }

    protected abstract void sorting(TransmittableThreadLocal<TraceContext> traceContextThreadLocal);

}
