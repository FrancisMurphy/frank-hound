package com.hbfintech.hound.core.acceptor.sorter;

import com.hbfintech.hound.core.support.Chain;
import com.hbfintech.hound.core.support.HoundInstanceFactory;
import lombok.Getter;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Sorter manager
 * @author frank
 */
public class SorterInitializer
{
    @Getter
    private Sorter firstSorter;

    public SorterInitializer()
    {
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void init()
    {
        LinkedList<Sorter> sorters = null;
        try
        {
            sorters = HoundInstanceFactory.getAllChildInstanceByClass(Sorter.class);
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
}
