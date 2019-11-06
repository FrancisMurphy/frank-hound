package com.hbfintech.hound.core.support;

import com.hbfintech.hound.core.acceptor.sorter.Sorter;
import com.hbfintech.hound.core.acceptor.unpacker.Unpacker;
import com.hbfintech.hound.core.requester.packer.Packer;
import lombok.Getter;
import lombok.NonNull;
import org.reflections.Reflections;

import java.util.*;

/**
 * hound组件注册器
 */
public class HoundComponentRegistry
{
    /**
     * hound组件mapper
     * {@link com.hbfintech.hound.core.support.HoundComponent}
     */
    private Map<Class, HoundComponentGroup> componentsMapper = new HashMap<>();

    public HoundComponentRegistry()
    {
        init();
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void init()
    {
        initComponent();
    }

    private void initComponent()
    {
        //初始化componentsMapper
        for(Class componentClazz: HoundClazzLibrary.VALID_HOUND_COMPONENT_CLAZZES)
        {
            componentsMapper.put(componentClazz,new HoundComponentGroup(componentClazz));
        }

        Map<Class<?>, HoundComponent> targetComponentClazzMap = getTargetHoundComponent();

        //实例话并注册组件
        parseComponentGroup(targetComponentClazzMap.entrySet());
    }

    private Map<Class<?>, HoundComponent> getTargetHoundComponent()
    {
        Reflections reflections = new Reflections("com.hbfintech.hound");
        Map<Class<?>, HoundComponent> targetComponentClazzMap = scanSpecifyPkgComponent(
                reflections);

        //TODO:后续支持通过配置，扫描其他包名下的hound拓展插件

        return targetComponentClazzMap;
    }

    private Map<Class<?>, HoundComponent> scanSpecifyPkgComponent(
            @NonNull Reflections reflections)
    {
        Set<Class<?>> targetComponentClazzSet = reflections.getTypesAnnotatedWith(
                HoundComponent.class);
        Map<Class<?>,HoundComponent> targetComponentClazzMap = new HashMap<>(targetComponentClazzSet.size());
        for(Class targetComponentClazz:targetComponentClazzSet)
        {
            targetComponentClazzMap.put(targetComponentClazz,(HoundComponent)targetComponentClazz.getAnnotation(HoundComponent.class));
        }
        return targetComponentClazzMap;
    }

    /**
     * 实例话组件
     * @param targetComponentClazzEntrySet
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void parseComponentGroup(@NonNull Set<Map.Entry<Class<?>, HoundComponent>> targetComponentClazzEntrySet)
    {

        for(Map.Entry<Class<?>, HoundComponent> targetComponentEntry:targetComponentClazzEntrySet)
        {
            String targetComponentName = targetComponentEntry.getValue().value();
            Class<?> targetComponentClazz = targetComponentEntry.getKey();

            for(Class validComponentClazz : HoundClazzLibrary.VALID_HOUND_COMPONENT_CLAZZES)
            {
                if(validComponentClazz.isAssignableFrom(targetComponentClazz))
                {
                    try
                    {
                        componentsMapper.get(validComponentClazz).add(targetComponentName,targetComponentClazz.newInstance());
                    }
                    catch (InstantiationException | IllegalAccessException e)
                    {
                        //do nothing
                    }
                }
            }
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public <T> HoundComponentGroup<T> getComponentsGroup(@NonNull Class<T> clazz)
    {
        return componentsMapper.get(clazz);
    }

    /**
     * The group of component
     * @param <T>
     */
    public class HoundComponentGroup<T>
    {
        @Getter
        private Class<T> componentClazz;

        private Map<String,T> componentInstanceMapper = new HashMap<>();

        public HoundComponentGroup(@NonNull Class<T> componentClazz)
        {
            this.componentClazz = componentClazz;
        }

        public void add(@NonNull String componentName, @NonNull T componentInstance)
        {
            componentInstanceMapper.put(componentName,componentInstance);
        }

        public T get(@NonNull String componentName)
        {
            return componentInstanceMapper.get(componentName);
        }
    }

}
