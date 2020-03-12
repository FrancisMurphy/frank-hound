package com.frank.hound.core.support;

import lombok.NonNull;
import org.reflections.Reflections;

import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.Set;

/**
 * Hound instance 工厂，目前只关注hound包
 * @author frank
 */
public class HoundBeanFactory
{
    private HoundBeanFactory()
    {
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static <T> LinkedList<T> getAllChildInstanceByClass(@NonNull Class<T> clazz)
            throws IllegalAccessException, InstantiationException
    {
        //TODO:目前暂时只扫描hound包路径
        Reflections reflections = new Reflections("com.frank.hound");
        Set<Class<? extends T>> subTypes = reflections.getSubTypesOf(clazz);

        LinkedList<T> instanceList = new LinkedList<>();

        for(Class targetClass : subTypes)
        {
            if(!(Modifier.isInterface(targetClass.getModifiers())
                    || Modifier.isAbstract(targetClass.getModifiers())))
            {
                instanceList.add((T)targetClass.newInstance());
            }
        }
        return instanceList;
    }
}
