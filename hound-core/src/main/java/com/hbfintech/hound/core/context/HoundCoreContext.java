package com.hbfintech.hound.core.context;

import com.hbfintech.hound.core.acceptor.sorter.Sorter;
import com.hbfintech.hound.core.acceptor.sorter.SorterLoader;
import com.hbfintech.hound.core.container.HoundBasicContainer;
import com.hbfintech.hound.core.container.HoundComponentContainer;

/**
 * 初始化并持有容器等重要实例的上下文，作用类似于ApplicationContext
 * @author frank
 */
public class HoundCoreContext implements HoundComponentContext, Sorter
{
    private static HoundCoreContext context = new HoundCoreContext();

    private HoundComponentContainer componentContainer;

    private Sorter firstSorter;

    private HoundCoreContext()
    {
        componentContainer = new HoundComponentContainer();
        SorterLoader sorterLoader= new SorterLoader();
        firstSorter = sorterLoader.getFirstSorter();
    }

    @Override
    public <T> T getComponent(String componentName,Class<T> componentClazz)
    {
        HoundBasicContainer<T> basicContainer = componentContainer.getCompontsContainer(componentClazz);
        if(basicContainer!=null)
        {
            return basicContainer.get(componentName);
        }
        return null;
    }

    public static HoundCoreContext getContext()
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
