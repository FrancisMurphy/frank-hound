package com.hbfintech.hound.core.support;

import ch.qos.logback.classic.LoggerContext;
import com.hbfintech.hound.core.acceptor.sorter.SorterInitializer;
import lombok.NonNull;
import org.slf4j.LoggerFactory;

/**
 * Initialization entry of hound-core, instantiated by single lazy mode
 * {@link Sheepehound#hound}
 * Sheepehound is an implementation of the hund interface that provides
 * the ability to initialize the portal's ioc and register corresponding hound beans
 * {@link com.hbfintech.hound.core.support.HoundSheep}
 *
 * @author frank
 */
public class Sheepehound implements Hound
{
    private LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

    private static Hound hound;

    private HoundEventNotifier houndEventNotifier;

    private HoundSheepRegistry sheepRegistry;

    private SorterInitializer sorterLoader;

    private HoundBridgeRegistry houndBridgeRegistry;

    private HoundBridgeAutowirer houndBridgeAutowirer;

    private Sheepehound()
    {
        sheepRegistry = new HoundSheepRegistry();
        sorterLoader= new SorterInitializer();
        houndBridgeRegistry = new HoundBridgeRegistry();
        houndBridgeAutowirer = new HoundBridgeAutowirer(houndBridgeRegistry);
        houndEventNotifier = new HoundEventNotifier();
    }

    public void init()
    {
        sheepRegistry.init();
        sorterLoader.init();
        houndBridgeRegistry.init();
        houndBridgeAutowirer.init();
        houndEventNotifier.init();

        publishEvent(new SheepehoundInitializedEvent(this,null));
    }

    @Override
    public <T> T getSheep(@NonNull String sheepName,@NonNull Class<T> sheepClazz)
    {
        HoundSheepRegistry.HoundSheepGroup<T> basicContainer = sheepRegistry
                .getSheepGroup(sheepClazz);
        if(basicContainer!=null)
        {
            return basicContainer.get(sheepName);
        }
        return null;
    }

    @Override
    public Object getBridge(@NonNull String bridgeName)
    {
        return houndBridgeRegistry.getBridge(bridgeName);
    }

    @Override
    public void publishEvent(@NonNull EventObject event)
    {
        houndEventNotifier.notify(event);
    }

    @Override
    public void sort()
    {
        //TODO： 待优化，准备使用单线程事件驱动模型，正在斟酌如何实现
        sorterLoader.getFirstSorter().sort();
    }

    public static Hound getHound()
    {
        if(null == hound)
        {
            hound = new Sheepehound();
            ((Sheepehound)hound).init();
        }
        return hound;
    }

}
