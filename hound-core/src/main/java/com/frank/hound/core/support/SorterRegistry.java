package com.frank.hound.core.support;

import com.frank.hound.core.acceptor.sorter.Sorter;
import com.frank.hound.core.common.Chain;
import com.frank.hound.core.common.Closeable;
import lombok.Getter;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Sorter manager
 * @author frank
 */
public class SorterRegistry implements Closeable
{
    @Getter
    private Sorter firstSorter;

    public SorterRegistry()
    {
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void init()
    {
        LinkedList<Sorter> sorters = null;
        try
        {
            sorters = HoundBeanFactory.getAllChildInstanceByClass(Sorter.class);
        }
        catch (IllegalAccessException | InstantiationException e)
        {
            //do nothing
            return;
        }

        if(null == sorters)
        {
            //do nothing
            return;
        }

        Iterator<Sorter> iterator=sorters.iterator();
        firstSorter = sorters.getFirst();
        Chain<Sorter> oldMan = null;
        while(iterator.hasNext()){
            if(oldMan != null)
            {
                Chain<Sorter> newGuy = (Chain<Sorter>) iterator.next();
                oldMan.setNext(iterator.next());
                oldMan = newGuy;
            }
            else
            {
                oldMan = (Chain<Sorter>) iterator.next();
            }
        }
    }

    @Override
    public void close()
    {
        firstSorter = null;
    }
}
