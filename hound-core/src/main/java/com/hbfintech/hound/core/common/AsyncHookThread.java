package com.hbfintech.hound.core.common;

import lombok.NonNull;

/**
 * @author frank
 */
public class AsyncHookThread extends Thread
{
    private HookListenerFunction hookListenerFunction;

    public AsyncHookThread(@NonNull HookListenerFunction hookListenerFunction)
    {
        this.hookListenerFunction = hookListenerFunction;
    }

    @Override
    public void run()
    {
        hookListenerFunction.hook();
    }
}
