package com.hbfintech.hound.core.support;

import java.lang.annotation.*;

/**
 * hound中组件的加载、获取、维护都是通过组件容器（类似Spring singleton）进行IOC,以此达成拓展、监控的目的
 * 只需要在对应的组件上加上此注解，Hound则会自动发现并且将器放入视作组件放入到Hound组件注册{@link com.hbfintech.hound.core.support.HoundComponentRegistry}器中
 * 需要组件的正常使用，除了声明此注解，对应的组件实现类还需要实现对应的能力接口
 * {@link com.hbfintech.hound.core.acceptor.sorter.Sorter} 分拣员
 * {@link com.hbfintech.hound.core.acceptor.unpacker.Unpacker} 拆包员
 * {@link com.hbfintech.hound.core.requester.packer.Packer} 封包员
 * 等能力接口，接口具体作用可查看设计文档，Hound在初始化完成之后会将其放入到合适的位置
 *
 */
@Documented
@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface HoundComponent
{
    /**
     * Equivalent to component name
     * @return
     */
    String value();
}
