package com.hbfintech.hound.core.support;

import com.hbfintech.hound.core.acceptor.sorter.Sorter;
import com.hbfintech.hound.core.acceptor.unpacker.Unpacker;
import com.hbfintech.hound.core.requester.packer.Packer;
import com.hbfintech.hound.core.requester.postman.Postman;
import lombok.NonNull;

/**
 * 使用配置重构此代码
 * @author frank
 */
public class HoundClazzLibrary
{
    public static final Class[] VALID_HOUND_SHEEP_CLAZZES = { Sorter.class, Packer.class,
            Unpacker.class, Postman.class };

    public static Class getHoundComponentClazz(@NonNull Class targetSheepClazz)
    {
        for(Class validSheepClazz:VALID_HOUND_SHEEP_CLAZZES)
        {
            if(validSheepClazz.isAssignableFrom(targetSheepClazz))
            {
                return validSheepClazz;
            }
        }
        return null;
    }
}
