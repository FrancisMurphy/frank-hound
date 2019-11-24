package com.hbfintech.hound.core.support;

/**
 * The root class from which all event state objects shall be derived.
 * All Events are constructed with a reference to the object, the "source",
 * that is logically deemed to be the object upon which the Event in question
 * initially occurred upon.
 * @author frank
 */

public class EventObject
{
    /**
     * The object on which the Event initially occurred.
     */
    protected Object source;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public EventObject(Object source)
    {
        if (source == null)
        {
            throw new IllegalArgumentException("null source");
        }

        this.source = source;
    }

    /**
     * The object on which the Event initially occurred.
     *
     * @return The object on which the Event initially occurred.
     */
    public Object getSource()
    {
        return source;
    }

    /**
     * Returns a String representation of this EventObject.
     *
     * @return A a String representation of this EventObject.
     */
    @Override
    public String toString()
    {
        return getClass().getName() + "[source=" + source + "]";
    }
}
