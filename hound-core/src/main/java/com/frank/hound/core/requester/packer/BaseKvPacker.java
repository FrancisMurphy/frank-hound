package com.frank.hound.core.requester.packer;

import com.frank.hound.core.context.ContextElement;
import com.frank.hound.core.context.TraceContext;
import com.frank.hound.core.support.TraceContextThreadLocalKeeper;
import com.sun.jndi.cosnaming.CNCtx;

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
            TraceContext traceContext = TraceContextThreadLocalKeeper.TRACE_LOCAL_CONTEXT
                    .get();

            if (null == traceContext)
            {
                return;
            }

            for (Map.Entry<String, ContextElement<?>> contextEntry : traceContext
                    .getContexts())
            {
                final String contextKey = contextEntry.getKey();
                final ContextElement contextElement = contextEntry.getValue();

                //目前核心上下文只能写入String，待编码器开发完成后，进行自动写入
                if(contextElement.isCoreContextElement()) {
                    final String contextValue = String.valueOf(contextElement.getObject());
                    unpackFunc.accept(contextKey,contextValue);
                }
            }
        }
        catch (Exception e)
        {
            //do nothing
        }

    }
}
