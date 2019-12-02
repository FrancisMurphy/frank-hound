package com.hbfintech.hound.core.requester.packer;

import com.hbfintech.hound.core.context.TraceContext;
import com.hbfintech.hound.core.support.TraceContextThreadLocalKeeper;

import java.util.Map;
import java.util.function.BiConsumer;

/**
 * @author frank
 */
public abstract class BaseKvPacker implements Packer
{
    @Override
    public void pack(BiConsumer<String, String> unpackFunc)
    {
        try
        {
            TraceContext traceContext = TraceContextThreadLocalKeeper.TRACE_TRACELOCAL_CONTEXT
                    .get();

            if (null == traceContext)
            {
                return;
            }

            for (Map.Entry<String, String> contextEntry : traceContext
                    .getContexts())
            {
                final String contextKey = contextEntry.getKey();
                final String contextValue = contextEntry.getValue();
                unpackFunc.accept(contextKey,contextValue);
            }
        }
        catch (Exception e)
        {
            //do nothing
        }

    }
}
