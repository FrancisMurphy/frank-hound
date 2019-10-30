package com.hbfintech.hound.core.util;

import org.reflections.Reflections;

import java.util.LinkedList;
import java.util.Set;

public class ReflectUtils
{
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static <T> LinkedList<T> getAllChildInstanceByClass(Class<T> clazz)
            throws IllegalAccessException, InstantiationException
    {
        Reflections reflections = new Reflections("com");
        Set<Class<? extends T>> subTypes = reflections.getSubTypesOf(clazz);

        LinkedList<T> instanceList = new LinkedList<>();

        for(Class targetClass : subTypes)
        {
            instanceList.add((T)targetClass.newInstance());
        }
        return instanceList;
    }
}
