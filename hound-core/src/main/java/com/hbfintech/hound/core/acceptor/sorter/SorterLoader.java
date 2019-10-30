package com.hbfintech.hound.core.acceptor.sorter;

import com.hbfintech.hound.core.util.ReflectUtils;
import lombok.Getter;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Sorter manager
 * @author frank
 */
public class SorterLoader
{
    @Getter
    private ChainSorter firstSorter;

    public SorterLoader()
    {
        initSorters();
    }

    private void initSorters()
    {
        LinkedList<ChainSorter> sorters = null;
        try
        {
            sorters = ReflectUtils.getAllChildInstanceByClass(ChainSorter.class);
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

        Iterator<ChainSorter> iterator=sorters.iterator();
        ChainSorter oldSorter = null;
        while(iterator.hasNext()){
            if(oldSorter != null)
            {
                ChainSorter newGuy = iterator.next();
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
