package com.frank.hound.core.support;

import lombok.NonNull;
import org.reflections.Reflections;

import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.Set;

/**
 * Hound instance 工厂，用于扫描
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
        Reflections reflections = new Reflections("com.hbfintech.hound");
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
