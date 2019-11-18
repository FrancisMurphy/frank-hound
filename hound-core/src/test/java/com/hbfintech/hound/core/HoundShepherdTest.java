package com.hbfintech.hound.core;

import com.hbfintech.hound.core.acceptor.unpacker.Unpacker;
import com.hbfintech.hound.core.support.Hound;
import com.hbfintech.hound.core.support.Sheepehound;

public class HoundShepherdTest
{
    public void houndShepherdInitTest()
    {
        Hound houndContext = Sheepehound.getContext();
        Unpacker mvcUnpacker = houndContext.getSheep("mvc", Unpacker.class);
    }
}
