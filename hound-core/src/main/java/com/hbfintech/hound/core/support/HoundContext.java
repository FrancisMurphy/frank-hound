package com.hbfintech.hound.core.support;

import com.hbfintech.hound.core.acceptor.sorter.Sorter;
import com.hbfintech.hound.core.acceptor.sorter.SorterInitializer;

/**
 * 初始化并持有容器等重要实例的上下文，作用类似于ApplicationContext
 * @author frank
 */
public class HoundContext implements HoundComponentLoader, Sorter
{
    private static HoundContext context = new HoundContext();

    private HoundComponentRegistry componentContainer;

    private Sorter firstSorter;

    private HoundContext()
    {
        componentContainer = new HoundComponentRegistry();
        SorterInitializer sorterLoader= new SorterInitializer();
        firstSorter = sorterLoader.getFirstSorter();
    }

    @Override
    public <T> T getComponent(String componentName,Class<T> componentClazz)
    {
        HoundComponentRegistry.HoundComponent<T> basicContainer = componentContainer.getCompontsContainer(componentClazz);
        if(basicContainer!=null)
        {
            return basicContainer.get(componentName);
        }
        return null;
    }

    public static HoundContext getContext()
    {
        return context;
    }

    @Override
    public void sort()
    {
        //TODO： 待优化，准备使用单线程事件驱动模型，正在斟酌如何实现
        firstSorter.sort();
    }
}
