package com.hbfintech.hound.core.support;

import lombok.Getter;

/**
 * @author frank
 */
public abstract class SheepehoundEvent extends EventObject
{
    @Getter
    private final String[] args;

    public SheepehoundEvent(Sheepehound source,String[] args)
    {
        super(source);
        this.args = args;
    }

    public Sheepehound getHoundShepherd() {
        return (Sheepehound) getSource();
    }
}
