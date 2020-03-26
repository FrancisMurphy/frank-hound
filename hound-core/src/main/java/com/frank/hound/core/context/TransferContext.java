package com.frank.hound.core.context;

import com.frank.hound.core.util.AddressUtils;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 传输上下文
 * @author frank
 */
@Data
public class TransferContext {

    /**
     * 时间戳
     */
    private Date timeStamp = new Date();

    /**
     * 来源ip
     */
    private String source = AddressUtils.getLocalIP();

    /**
     * 传输数据
     */
    private Map<String,String> data = new HashMap<>();

    public TransferContext(@NonNull final TraceContext traceContext) {
        traceContext.getContexts().forEach(contextEntry -> data.put(contextEntry.getKey(),contextEntry.getValue().getData()));
    }

}
