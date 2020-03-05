package com.frank.hound.core.support;

import com.frank.hound.core.common.BasePkgRegistry;
import com.frank.hound.core.common.Closeable;
import com.frank.hound.core.env.HoundEnvironment;
import lombok.Getter;
import lombok.NonNull;
import org.reflections.Reflections;

import java.util.*;

/**
 * The hound sheep registry that find and instantiate
 * the hound sheep what declare {@link HoundSheep}
 * and register it in this instance {@link SheepRegistry#sheepMapper}.
 *
 * @author frank
 */
public class SheepRegistry extends BasePkgRegistry
        implements Closeable
{

    /**
     * hound组件mapper
     * {@link HoundSheep}
     */
    private Map<Class, HoundSheepGroup> sheepMapper = new HashMap<>();

    public SheepRegistry()
    {
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void init()
    {
        catchSheep();
    }

    private void catchSheep()
    {
        //初始化componentsMapper
        for(Class sheepClazz: HoundClazzLibrary.VALID_HOUND_SHEEP_CLAZZES)
        {
            sheepMapper.put(sheepClazz,new HoundSheepGroup(sheepClazz));
        }

        Map<Class<?>, HoundSheep> targetSheepClazzMap = getTargetHoundSheep();

        //实例话并注册组件
        parseSheepGroup(targetSheepClazzMap.entrySet());
    }

    private Map<Class<?>, HoundSheep> getTargetHoundSheep()
    {
        Map<Class<?>, HoundSheep> targetSheepClazzMap = scanSpecifyPkgComponent(
                DEFAULT_BASE_PKG);

        //TODO:后续支持通过配置，扫描其他包名下的hound拓展插件

        return targetSheepClazzMap;
    }

    private Map<Class<?>, HoundSheep> scanSpecifyPkgComponent(
            @NonNull String scanPkg)
    {
        Reflections reflections;

        try
        {
            reflections = new Reflections(scanPkg);
        }
        catch (Exception e)
        {
            return new HashMap<>(0);
        }

        Set<Class<?>> targetSheepClazzSet = reflections.getTypesAnnotatedWith(
                HoundSheep.class);
        Map<Class<?>, HoundSheep> targetSheepClazzMap = new HashMap<>(targetSheepClazzSet.size());
        for(Class targetSheepClazz:targetSheepClazzSet)
        {
            targetSheepClazzMap.put(targetSheepClazz,(HoundSheep)targetSheepClazz.getAnnotation(
                    HoundSheep.class));
        }
        return targetSheepClazzMap;
    }

    /**
     * 实例话组件
     * @param targetSheepClazzEntrySet
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void parseSheepGroup(@NonNull Set<Map.Entry<Class<?>, HoundSheep>> targetSheepClazzEntrySet)
    {

        for(Map.Entry<Class<?>, HoundSheep> targetSheepEntry:targetSheepClazzEntrySet)
        {
            String targetSheepName = targetSheepEntry.getValue().value();
            Class<?> targetSheepClazz = targetSheepEntry.getKey();

            for(Class validSheepClazz : HoundClazzLibrary.VALID_HOUND_SHEEP_CLAZZES)
            {
                if(validSheepClazz.isAssignableFrom(targetSheepClazz))
                {
                    try
                    {
                        sheepMapper.get(validSheepClazz).add(targetSheepName,targetSheepClazz.newInstance());
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
    public <T> HoundSheepGroup<T> getSheepGroup(@NonNull Class<T> clazz)
    {
        return sheepMapper.get(clazz);
    }

    @Override
    public void close()
    {
        sheepMapper.clear();
    }

    @Override
    public void refresh(HoundEnvironment houndEnvironment)
    {
//        String configBasePkg
    }

    /**
     * The group of component
     * @param <T>
     */
    public class HoundSheepGroup<T>
    {
        @Getter
        private Class<T> sheepClazz;

        private Map<String,T> sheepInstanceMapper = new HashMap<>();

        public HoundSheepGroup(@NonNull Class<T> sheepClazz)
        {
            this.sheepClazz = sheepClazz;
        }

        public void add(@NonNull String sheepName, @NonNull T sheepInstance)
        {
            sheepInstanceMapper.put(sheepName,sheepInstance);
        }

        public T get(@NonNull String sheepName)
        {
            return sheepInstanceMapper.get(sheepName);
        }
    }

}
