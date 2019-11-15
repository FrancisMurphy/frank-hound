package com.hbfintech.hound.core.support;

import lombok.Getter;

/**
 * @author frank
 */
public abstract class HoundShepherdEvent extends EventObject
{
    @Getter
    private final String[] args;

    public HoundShepherdEvent(HoundShepherd source,String[] args)
    {
        super(source);
        this.args = args;
    }

    public HoundShepherd getHoundShepherd() {
        return (HoundShepherd) getSource();
    }
}
