package com.hbfintech.hound.core.support;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class HoundEventListenerRegistry
{
    private final List<SheepehoundEventListener> shepherdEventListeners = new LinkedList<>();

    public HoundEventListenerRegistry()
    {
        //do nothing
    }

    public void init()
    {
        synchronized (shepherdEventListeners)
        {
            //获取继承HoundShepherdEventListener接口的监听器
            LinkedList<SheepehoundEventListener> newShepherdEventListeners = null;
            try
            {
                shepherdEventListeners.clear();
                shepherdEventListeners.addAll(HoundInstanceFactory
                        .getAllChildInstanceByClass(
                                SheepehoundEventListener.class));
            }
            catch (IllegalAccessException | InstantiationException e)
            {
                //do nothing
                return;
            }
        }
    }

    public Iterator<SheepehoundEventListener> getShepherdEventListeners()
    {
        return shepherdEventListeners.iterator();
    }
}
