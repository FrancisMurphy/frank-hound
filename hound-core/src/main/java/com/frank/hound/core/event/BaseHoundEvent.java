package com.frank.hound.core.event;

import com.frank.hound.core.support.EventObject;
import com.frank.hound.core.support.Hound;
import lombok.Getter;
import lombok.NonNull;

/**
 * @author frank
 */
public abstract class BaseHoundEvent extends EventObject
{
    @Getter
    private final String[] args;

    public BaseHoundEvent(Object source)
    {
        super(source);
        this.args = null;
    }

    public BaseHoundEvent(@NonNull Hound source,String[] args)
    {
        super(source);
        this.args = args;
    }

    public Hound getHoundShepherd() {
        return (Hound) getSource();
    }
}
