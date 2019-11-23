package com.hbfintech.hound.core.common;

import lombok.NonNull;

/**
 * @author frank
 */
public class AsyncSimpleFuncThread extends Thread
{
    private SimpleFunction simpleFunction;

    public AsyncSimpleFuncThread(@NonNull SimpleFunction simpleFunction)
    {
        this.simpleFunction = simpleFunction;
    }

    @Override
    public void run()
    {
        simpleFunction.func();
    }
}
