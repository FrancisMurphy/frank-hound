package com.hbfintech.hound.core.acceptor.unpacker;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.hbfintech.hound.core.context.TraceContext;
import com.hbfintech.hound.core.event.SortEvent;
import com.hbfintech.hound.core.event.UnpackedEvent;
import com.hbfintech.hound.core.support.Hound;
import com.hbfintech.hound.core.support.Sheepehound;
import com.hbfintech.hound.core.support.TraceContextAssistant;
import com.hbfintech.hound.core.support.TraceContextThreadLocalKeeper;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author frank
 */
@Slf4j
public abstract class BaseKvUnpacker implements Unpacker
{
    private Hound hound = Sheepehound.getHound();

    @Override
    public void unpack(@NonNull Map<String, String> unpackKvMapper)
    {
        try
        {
            TransmittableThreadLocal<TraceContext> traceContextThreadLocal = TraceContextThreadLocalKeeper.TRACE_LOCAL_CONTEXT;

            for (Map.Entry<String, String> unpackMaterial : unpackKvMapper
                    .entrySet())
            {
                final String k = unpackMaterial.getKey();
                final String v = unpackMaterial.getValue();
                if (TraceContextAssistant.isTraceKeyContain(k))
                {
                    //放入线程上下文
                    TraceContext traceContext = new TraceContext();
                    traceContext.addContext(k, v);
                    traceContextThreadLocal.set(traceContext);
                }
            }

            hound.publishEvent(new UnpackedEvent(hound));

            hound.publishEvent(new SortEvent(hound,hound::sort));
        }
        catch (Exception e)
        {
            log.error("Hound BaseKvUnpacker error:",e);
        }
    }
}
