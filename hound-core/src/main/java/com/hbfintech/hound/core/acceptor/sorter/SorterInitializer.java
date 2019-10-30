package com.hbfintech.hound.core.acceptor.sorter;

import com.hbfintech.hound.core.util.ReflectUtils;
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
    private BaseSorter firstSorter;

    public SorterInitializer()
    {
        initSorters();
    }

    private void initSorters()
    {
        LinkedList<BaseSorter> sorters = null;
        try
        {
            sorters = ReflectUtils.getAllChildInstanceByClass(BaseSorter.class);
        }
        catch (IllegalAccessException | InstantiationException e)
        {
            return;
            //TODO:专用异常体系
        }

        if(null == sorters)
        {
            return;
        }

        Iterator<BaseSorter> iterator=sorters.iterator();
        BaseSorter oldSorter = null;
        while(iterator.hasNext()){
            if(oldSorter != null)
            {
                BaseSorter newGuy = iterator.next();
                oldSorter.setNextSorter(newGuy);
                oldSorter = newGuy;
            }
            else
            {
                oldSorter = iterator.next();
            }
        }

        firstSorter = sorters.getFirst();
    }
}
