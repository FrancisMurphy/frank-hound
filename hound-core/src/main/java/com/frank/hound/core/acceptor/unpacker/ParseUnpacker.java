package com.frank.hound.core.acceptor.unpacker;

import com.frank.hound.core.codec.Codec;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public abstract class ParseUnpacker extends BaseUnpacker{

    private Codec codec;

    @Override
    public void unpack(String fhtc) {





        Map<String,String> traceContextMap = new HashMap<>();


        unpackTraceContextMap(traceContextMap);
    }


}
