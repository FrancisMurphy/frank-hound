package com.frank.hound.core.support;

import com.frank.hound.core.common.Closeable;
import com.frank.hound.core.listener.HoundEventListener;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Hound event listener registry
 * @author frank
 */
public class HoundEventListenerRegistry implements Closeable
{
    private final List<HoundEventListener> shepherdEventListeners = new LinkedList<>();

    public HoundEventListenerRegistry()
    {
        //do nothing
    }

    /**
     * This method is thread safe
     */
    public void init()
    {
        synchronized (shepherdEventListeners)
        {
            //Get listener that inherits the HoundShepherdEventListener interface
            try
            {
                shepherdEventListeners.clear();
                shepherdEventListeners.addAll(HoundBeanFactory
                        .getAllChildInstanceByClass(
                                HoundEventListener.class));
            }
            catch (IllegalAccessException | InstantiationException e)
            {
                //do nothing
            }
        }
    }

    public Iterator<HoundEventListener> getShepherdEventListeners()
    {
        return shepherdEventListeners.iterator();
    }

    @Override
    public void close()
    {
        shepherdEventListeners.clear();
    }
}
