package com.hbfintech.hound.core.listener;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.hbfintech.hound.core.common.StringUtils;
import com.hbfintech.hound.core.constant.TraceContextConstants;
import com.hbfintech.hound.core.context.TraceContext;
import com.hbfintech.hound.core.event.BaseHoundEvent;
import com.hbfintech.hound.core.event.UnpackedEvent;
import com.hbfintech.hound.core.support.TraceContextThreadLocalKeeper;

import java.util.UUID;

/**
 * 用于Hound在接收到追踪请求后进行TraceContext
 *
 * @author frank
 */
public class UnpackedTraceContextInitListener implements HoundEventListener {
    private TransmittableThreadLocal<TraceContext> traceContextThreadLocal =
        TraceContextThreadLocalKeeper.TRACE_LOCAL_CONTEXT;

    @Override
    public void onEvent(BaseHoundEvent event) {
        if (event instanceof UnpackedEvent) {
            initTraceContext(traceContextThreadLocal);
        }
    }

    /**
     * Init traceId that can not find traceId in trace context...
     *
     * @param traceContextThreadLocal
     *            threadlocal
     * @return
     */
    private void initTraceContext(TransmittableThreadLocal<TraceContext> traceContextThreadLocal) {
        if (null == traceContextThreadLocal.get()) {
            UUID uuid = UUID.randomUUID();
            String newTraceId = uuid.toString().replace("-", "");

            TraceContext traceContext = new TraceContext();
            traceContext.addContext(TraceContextConstants.TRACE_CONTEXT_HEAD, newTraceId);
            traceContextThreadLocal.set(traceContext);
        } else if (StringUtils
            .isEmpty(traceContextThreadLocal.get().getContext(TraceContextConstants.TRACE_CONTEXT_HEAD))) {
            UUID uuid = UUID.randomUUID();
            String newTraceId = uuid.toString().replace("-", "");

            traceContextThreadLocal.get().addContext(TraceContextConstants.TRACE_CONTEXT_HEAD, newTraceId);
        }
    }
}
