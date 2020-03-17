package com.frank.hound.core.context;

import com.frank.hound.core.constant.ContextElementType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 上下文元素
 * @author frank
 * @param <T> 元素类型
 */
@AllArgsConstructor
public class ContextElement<T> {

    @Getter
    private ContextElementType type;

    @Setter
    @Getter
    private T object;

    @Setter
    @Getter
    private Class<T> clazz;

    public boolean isCoreContextElement() {
        return ContextElementType.CORE_CONTEXT.equals(type);
    }
}
