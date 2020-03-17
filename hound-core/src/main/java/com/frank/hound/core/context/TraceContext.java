package com.frank.hound.core.context;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * The instance of this class is thread-safe
 * @author frank
 */
public class TraceContext
{
    /**
     * The map that keep context param
     * 1."trace_id" {@see com.frank.trace.link.constant.TraceContextConstant}
     */
    private Map<String,ContextElement<?>> contextMap;

    public ContextElement<?> getContext(String contextKey)
    {
        if(contextMap!=null)
        {
            try {
                return contextMap.get(contextKey);
            }catch (ClassCastException e) {
                return null;
            }
        }
        return null;
    }

    public <T> void addContext(String contextKey, ContextElement<T> contextValue)
    {
        if(null == contextMap)
        {
            contextMap = new HashMap<>(10);
        }
        this.contextMap.put(contextKey,contextValue);
    }

    public Set<Map.Entry<String,ContextElement<?>>>getContexts()
    {
        if(null == contextMap)
        {
            return new HashSet<>();
        }
        return contextMap.entrySet();
    }
}
