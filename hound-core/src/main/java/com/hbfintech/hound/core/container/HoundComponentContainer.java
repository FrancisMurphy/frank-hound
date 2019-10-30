package com.hbfintech.hound.core.container;

import com.hbfintech.hound.core.annotation.HoundComponent;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 二级映射容器
 */
public class HoundComponentContainer
{
    private Map<Class,HoundBasicContainer> componentsMapper = new HashMap<>();

    public HoundComponentContainer()
    {
        initContainer();
    }

    private void initContainer()
    {
        //扫描方法区下的类信息
        Reflections reflections = new Reflections("com");
        Set<Class<?>> componentClazzSet = reflections.getTypesAnnotatedWith(HoundComponent.class);

        parseComponent(componentClazzSet);

    }

    private void parseComponent(Set<Class<?>> componentClazzSet)
    {



    }

    public <T> HoundBasicContainer<T> getCompontsContainer(Class<T> clazz)
    {
        return componentsMapper.get(clazz);
    }

    //    public static void main(String[] args)
//    {
//        HoundComponentContainer container = new HoundComponentContainer();
//        Unpack mvcUnpack = container.getCompontsContainer(Unpack.class).get("mvc");
//
//
//    }

}
