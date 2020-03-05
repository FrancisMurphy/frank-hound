package com.frank.hound.core.support;

import com.frank.hound.core.common.Closeable;
import lombok.NonNull;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author frank
 */
public class HoundAutoCloser
{
    private Set<Closeable> closerList = new LinkedHashSet<>();

    public void registerAutoCloser(@NonNull Closeable... closers)
    {
        if(closers.length == 0)
        {
            return;
        }

        for(Closeable close:closers)
        {
            closerList.add(close);
        }
    }

    public void exec()
    {
        for(Closeable closer:closerList)
        {
            closer.close();
        }
    }
}
