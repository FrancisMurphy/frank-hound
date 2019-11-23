package com.hbfintech.hound.core.listener;

import com.hbfintech.hound.core.event.HoundEvent;
import com.hbfintech.hound.core.event.SortEvent;

/**
 * @author frank
 */
public class SortEventListener implements HoundEventListener
{
    @Override
    public void onEvent(HoundEvent event)
    {
        if(event instanceof SortEvent)
        {
            SortEvent sortEvent = (SortEvent)event;
            sortEvent.sort();
        }
    }
}
