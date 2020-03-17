package com.frank.hound.core.constant;

/**
 * 上下文类型拓展
 * @author frank
 */
public enum ContextElementType {
    /**
     * 核心上下文
     */
    CORE_CONTEXT,
    /**
     * 内部拓展上下文，仅供内部插件进行使用
     */
    INNER_EXPAND_CONTEXT,
    /**
     * 外部拓展用上下文类型，供第三方Hound插件进行拓展
     */
    EXTER_EXPAND_CONTEXT;
}
