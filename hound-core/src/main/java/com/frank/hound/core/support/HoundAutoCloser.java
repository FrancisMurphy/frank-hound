package com.frank.hound.core.support;

import com.frank.hound.core.common.Closeable;
import lombok.NonNull;

import java.util.Arrays;
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

        closerList.addAll(Arrays.asList(closers));
    }

    public void exec()
    {
        for(Closeable closer:closerList)
        {
            closer.close();
        }
    }
}
