package com.frank.hound.core.codec.protocol.http;

import com.frank.hound.core.codec.protocol.ProtocolComposition;
import com.frank.hound.core.codec.protocol.ProtocolDescribable;
import com.frank.hound.core.codec.serialize.SerializeNotationEnum;
import lombok.AllArgsConstructor;

import java.util.List;

/**
 * Http协议描述
 * @author frank
 */
@AllArgsConstructor
public class HttpProtocolDescribe implements ProtocolDescribable {

    private SerializeNotationEnum serializeNotation;

    private List<ProtocolComposition> protocolCompositions;

    @Override
    public SerializeNotationEnum getSerializeNotation() {
        return serializeNotation;
    }

    @Override
    public List<ProtocolComposition> getProtocolComposition() {
        return protocolCompositions;
    }
}
