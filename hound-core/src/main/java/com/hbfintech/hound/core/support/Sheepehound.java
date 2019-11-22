package com.hbfintech.hound.core.support;

import ch.qos.logback.classic.LoggerContext;
import com.hbfintech.hound.core.acceptor.sorter.SorterInitializer;
import com.hbfintech.hound.core.common.AsyncHookThread;
import com.hbfintech.hound.core.event.HoundInitializedEvent;
import lombok.NonNull;
import org.slf4j.LoggerFactory;

/**
 * Initialization entry of hound-core, instantiated by single lazy mode
 * {@link Sheepehound#hound}，that Avoid the destruction of a single case by
 * means of a private constructor and a synchronization method block.
 * Sheepehound is an implementation of the hound interface that provides
 * the ability to initialize the portal's ioc and register corresponding hound beans
 * {@link com.hbfintech.hound.core.support.HoundSheep}
 *
 *
 * @author frank
 */
public class Sheepehound implements Hound
{
    private LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

    private static Hound hound;

    private HoundEventNotifier houndEventNotifier;

    private HoundSheepRegistry sheepRegistry;

    private SorterInitializer sorterInitializer;

    private HoundBridgeRegistry houndBridgeRegistry;

    private HoundBridgeAutowirer houndBridgeAutowirer;

    private HoundEventListenerRegistry houndEventListenerRegistry;

    private Sheepehound()
    {
        sheepRegistry = new HoundSheepRegistry();
        sorterInitializer = new SorterInitializer();
        houndBridgeRegistry = new HoundBridgeRegistry();
        houndBridgeAutowirer = new HoundBridgeAutowirer();
        houndEventListenerRegistry = new HoundEventListenerRegistry();
        houndEventNotifier = new HoundEventNotifier(houndEventListenerRegistry);
    }

    private void init()
    {
        //Init
        sheepRegistry.init();
        sorterInitializer.init();
        houndBridgeRegistry.init();
        houndBridgeAutowirer.init(houndBridgeRegistry);
        houndEventListenerRegistry.init();

        //Publish initialized event
        publishEvent(new HoundInitializedEvent(this,null));

        //Register close hook
        Runtime.getRuntime().addShutdownHook(new AsyncHookThread(this::close));
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
        sorterInitializer.getFirstSorter().sort();
    }

    public static synchronized Hound getHound()
    {
        if(null == hound)
        {
            hound = new Sheepehound();
            ((Sheepehound)hound).init();
        }
        return hound;
    }

    /**
     * Avoid being called by an external method in the form of privatization of the method
     */
    private void close()
    {
        sheepRegistry.close();
        sheepRegistry = null;
        houndBridgeRegistry.close();
        houndBridgeRegistry = null;
        houndEventListenerRegistry.close();
        houndEventListenerRegistry = null;
    }
}
