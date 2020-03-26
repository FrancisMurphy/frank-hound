package com.frank.hound.core.requester.packer;

import com.frank.hound.core.codec.Codec;
import com.frank.hound.core.context.TraceContext;
import com.frank.hound.core.context.TransferContext;
import com.frank.hound.core.support.HoundCodec;
import com.frank.hound.core.support.TraceContextThreadLocalKeeper;
import lombok.NonNull;

import java.util.function.BiConsumer;

/**
 * @author frank
 */
public abstract class BaseKvPacker implements Packer {

    @HoundCodec
    private Codec codec;

    @Override
    public void pack(@NonNull BiConsumer<String, String> unpackFunc) {
        try {
            TraceContext traceContext = TraceContextThreadLocalKeeper.TRACE_LOCAL_CONTEXT.get();

            if (null == traceContext) {
                return;
            }

            final String transferContent = codec.build(new TransferContext(traceContext));

            //将上下文追踪信息
            unpackFunc.accept("fhtc",transferContent);

        } catch (Exception e) {
            // do nothing
        }

    }
}
