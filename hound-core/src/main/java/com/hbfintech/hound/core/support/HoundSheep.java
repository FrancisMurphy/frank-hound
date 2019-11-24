package com.hbfintech.hound.core.support;

import java.lang.annotation.*;

/**
 * Sheep managed by hound that can be understood as a java bean.
 * The loading, acquisition and maintenance of sheep in hound are all through the bean
 * container (similar to Spring singleton) for IOC, to achieve the purpose of expansion and monitoring.
 * By simply adding this note to the corresponding bean, Hound automatically discovers and puts
 * the device in as a bean into the Hound`s {@link SheepRegistry}
 * The normal use of the bean is required, and in addition to declaring this note,
 * the corresponding bean implementation class also needs to implement the interface capability correspondingly
 * {@link com.hbfintech.hound.core.acceptor.sorter.Sorter}
 * {@link com.hbfintech.hound.core.acceptor.unpacker.Unpacker}
 * {@link com.hbfintech.hound.core.requester.packer.Packer}
 * The specific function of the interface can be seen in the design document,
 * and the hund will put it in the right place after the initialization is completed.
 *
 * @author frank
 */
@Documented
@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface HoundSheep
{
    /**
     * Equivalent to sheep name
     * @return
     */
    String value();
}
