package com.hbfintech.hound.core.support;

import lombok.NonNull;
import org.reflections.Reflections;

import java.util.LinkedList;
import java.util.Set;

public class HoundInstanceFactory
{
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static <T> LinkedList<T> getAllChildInstanceByClass(@NonNull Class<T> clazz)
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
