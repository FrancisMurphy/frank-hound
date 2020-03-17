package com.frank.hound.core.codec.protocol;

import com.frank.hound.core.codec.serialize.SerializeNotationEnum;

/**
 * 协议描述
 * @author frank
 */
public interface ProtocolDescribable {

    SerializeNotationEnum getSerializeNotation();

    ParsedContent getParsedContent();

    OriginalContent getOriginalContext();
}
