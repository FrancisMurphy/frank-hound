package com.frank.hound.core.support;

import lombok.NonNull;

/**
 * Exception for hound initialization
 * @author frank
 */
public class HoundInitException extends HoundException{

    public HoundInitException(@NonNull Class<?> sourceClazz,@NonNull String houndExceptionMsg) {
        super(sourceClazz,"Init exception: "+houndExceptionMsg);
    }

    public HoundInitException(@NonNull Class<?> sourceClazz,@NonNull String houndExceptionMsg,@NonNull Throwable originalStack) {
        super(sourceClazz,"Init exception: "+houndExceptionMsg,originalStack);
    }

}
