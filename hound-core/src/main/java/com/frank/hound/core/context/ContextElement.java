package com.frank.hound.core.context;

import com.frank.hound.core.constant.ContextElementType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 上下文元素
 * @author frank
 */
@AllArgsConstructor
public class ContextElement {

    @Getter
    private ContextElementType type;

    @Setter
    @Getter
    private String data;

    public boolean isCoreContextElement() {
        return ContextElementType.CORE_CONTEXT.equals(type);
    }
}
