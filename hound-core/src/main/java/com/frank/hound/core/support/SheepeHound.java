package com.frank.hound.core.support;

import ch.qos.logback.classic.LoggerContext;
import com.frank.hound.core.common.AsyncSimpleFuncThread;
import com.frank.hound.core.env.BaseHoundEnvironment;
import com.frank.hound.core.env.HoundConfigurableEnvironment;
import com.frank.hound.core.env.HoundEnvironment;
import com.frank.hound.core.env.HoundNativeEnvironment;
import com.frank.hound.core.event.HoundInitializedEvent;
import lombok.NonNull;
import org.slf4j.LoggerFactory;

/**
 * Initialization entry of hound-core, instantiated by single lazy mode
 * {@link SheepeHound#hound}，that Avoid the destruction of a single case by
 * means of a private constructor and a synchronization method block.
 * Sheepehound is an implementation of the hound interface that provides
 * the ability to initialize the portal's ioc and register corresponding hound beans
 * {@link com.frank.hound.core.support.HoundSheep}
 *
 * @author frank
 */
public class SheepeHound implements Hound
{
    private final LoggerContext loggerContext = (LoggerContext) LoggerFactory
            .getILoggerFactory();

    private static Hound hound;

    private HoundEventNotifier houndEventNotifier;

    /**
     * sheep bean registry
     */
    private SheepRegistry sheepRegistry;

    /**
     * sorter bean registry
     */
    private SorterRegistry sorterRegistry;

    /**
     * bridge bean registry
     */
    private BridgeRegistry bridgeRegistry;

    /**
     * The autowirer for bridge bean
     */
    private BridgeAutowirer bridgeAutowirer;

    /**
     * hound event listener registry
     */
    private HoundEventListenerRegistry houndEventListenerRegistry;

    /**
     * the closer for hound
     */
    private HoundAutoCloser autoCloser;

    /**
     * hound native environment
     */
    private HoundConfigurableEnvironment nativeEnvironment;

    /**
     * hound external environment
     */
//    private HoundConfigurableEnvironment externalEnvironment;

    private SheepeHound()
    {
        // TODO:待使用工厂模式返回
        nativeEnvironment = new HoundNativeEnvironment(new BaseHoundEnvironment());
        sheepRegistry = new SheepRegistry();
        sorterRegistry = new SorterRegistry();
        bridgeRegistry = new BridgeRegistry();
        bridgeAutowirer = new BridgeAutowirer();
        houndEventListenerRegistry = new HoundEventListenerRegistry();
        houndEventNotifier = new HoundEventNotifier(houndEventListenerRegistry);
        autoCloser = new HoundAutoCloser();
    }

    private void init()
    {
        try{
            //Init  native environment
            nativeEnvironment.refresh();

            //Init hound sheep
            sheepRegistry.init(nativeEnvironment);
            sorterRegistry.init();
            bridgeRegistry.init(nativeEnvironment);
            bridgeAutowirer.init(bridgeRegistry);
            houndEventListenerRegistry.init();

            //Publish initialized event
            publishEvent(new HoundInitializedEvent(this));

            //Register closer which can release related resources at the end of the process automatically
            autoCloser.registerAutoCloser(sheepRegistry, sorterRegistry,
                bridgeRegistry, houndEventListenerRegistry);

            //Register close hook func
            Runtime.getRuntime()
                .addShutdownHook(new AsyncSimpleFuncThread(this::close));
        }catch (HoundException e){
            //print log, finish init...
            loggerContext.getLogger(getClass()).error("Init hound fail, error:",e);
            //exec hook
            new AsyncSimpleFuncThread(this::close).run();
        }
    }

    @Override
    public <T> T getSheep(@NonNull String sheepName,
            @NonNull Class<T> sheepClazz)
    {
        SheepRegistry.HoundSheepGroup<T> basicContainer = sheepRegistry
                .getSheepGroup(sheepClazz);
        if (basicContainer != null)
        {
            return basicContainer.get(sheepName);
        }
        return null;
    }

    @Override
    public Object getBridge(@NonNull String bridgeName)
    {
        return bridgeRegistry.getBridge(bridgeName);
    }

    @Override
    public void publishEvent(@NonNull EventObject event)
    {
        houndEventNotifier.notify(event);
    }

    @Override
    public void sort()
    {
        sorterRegistry.getFirstSorter().sort();
    }

    @Override
    public HoundEnvironment getEnvironment()
    {
        return nativeEnvironment;
    }

    public static synchronized Hound getHound()
    {
        if (null == hound)
        {
            hound = new SheepeHound();
            ((SheepeHound) hound).init();
        }
        return hound;
    }

    /**
     * Avoid being called by an external method in the form of privatization of the method
     */
    private void close()
    {
        autoCloser.exec();
    }
}
