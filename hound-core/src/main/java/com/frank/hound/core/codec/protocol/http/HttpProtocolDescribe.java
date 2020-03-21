package com.frank.hound.core.codec.protocol.http;

import com.frank.hound.core.codec.protocol.OriginalContent;
import com.frank.hound.core.codec.protocol.ParsedContent;
import com.frank.hound.core.codec.protocol.ProtocolDescribable;
import com.frank.hound.core.codec.serialize.SerializeNotationEnum;
import lombok.AllArgsConstructor;

/**
 * Http协议描述
 *
 * @author frank
 */
@AllArgsConstructor
public class HttpProtocolDescribe implements ProtocolDescribable {

    private SerializeNotationEnum serializeNotation;

    private ParsedContent parsedContent;

    private OriginalContent originalContent;

    public HttpProtocolDescribe() {
    }

    @Override
    public SerializeNotationEnum getSerializeNotation() {
        return serializeNotation;
    }

    @Override
    public ParsedContent getParsedContent() {
        return parsedContent;
    }

    @Override
    public OriginalContent getOriginalContext() {
        return originalContent;
    }
}
