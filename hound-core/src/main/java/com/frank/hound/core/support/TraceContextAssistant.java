package com.frank.hound.core.support;

import com.frank.hound.core.common.StringUtils;
import com.frank.hound.core.constant.TraceContextConstants;
import lombok.NonNull;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

//TODO:需要重写，垃圾代码
public class TraceContextAssistant
{
    private static Set<String> traceContextKeys = new HashSet<>();

    static
    {
        Field[] traceContextFields = TraceContextConstants.class.getFields();

        try
        {
            for (Field traceContextField : traceContextFields)
            {
                String traceContextKey = (String) traceContextField.get(null);
                if (!StringUtils.isEmpty(traceContextKey))
                {
                    traceContextKeys.add(traceContextKey);
                }
            }
        }
        catch (IllegalAccessException e)
        {
//            e.printStackTrace();
        }
    }

    public static boolean isTraceKeyContain(@NonNull String key)
    {
        return traceContextKeys.contains(key);
    }

}
