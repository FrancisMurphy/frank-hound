package com.frank.hound.core.acceptor.unpacker;

import com.frank.hound.core.codec.Codec;
import com.frank.hound.core.context.TransferContext;
import com.frank.hound.core.support.HoundCodec;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author frank
 */
@Slf4j
public abstract class BaseParseUnpacker extends BaseUnpacker{

    @HoundCodec
    private Codec codec;

    @Override
    public void unpack(@NonNull String fhtc) {
        TransferContext transferContext = codec.parseFrom(fhtc, TransferContext.class);
        if(transferContext!=null) {
            unpackTraceContextMap(transferContext.getData());
        }
    }
}
