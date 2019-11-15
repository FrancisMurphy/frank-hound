package com.hbfintech.hound.core.support;

import lombok.Getter;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class HoundEventListenerRegistry
{
    private final List<HoundShepherdEventListener> shepherdEventListeners = new LinkedList<>();

    public HoundEventListenerRegistry()
    {
        //do nothing
    }

    public void init()
    {
        synchronized (shepherdEventListeners)
        {
            //获取继承HoundShepherdEventListener接口的监听器
            LinkedList<HoundShepherdEventListener> newShepherdEventListeners = null;
            try
            {
                newShepherdEventListeners = HoundInstanceFactory
                        .getAllChildInstanceByClass(
                                HoundShepherdEventListener.class);
            }
            catch (IllegalAccessException | InstantiationException e)
            {
                //do nothing
                return;
            }
        }
    }

    public Iterator<HoundShepherdEventListener> getShepherdEventListeners()
    {
        return shepherdEventListeners.iterator();
    }
}
