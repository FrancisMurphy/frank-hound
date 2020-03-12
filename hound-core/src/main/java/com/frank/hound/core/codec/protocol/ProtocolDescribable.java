package com.frank.hound.core.codec.protocol;

import com.frank.hound.core.codec.serialize.SerializeNotationEnum;

import java.util.List;

/**
 * 协议描述
 * @author frank
 */
public interface ProtocolDescribable {

    SerializeNotationEnum getSerializeNotation();

    List<ProtocolComposition> getProtocolComposition();
}
