package com.frank.hound.core;

import com.frank.hound.core.acceptor.unpacker.Unpacker;
import com.frank.hound.core.support.Hound;
import com.frank.hound.core.support.Sheepehound;

public class HoundShepherdTest
{
    public void houndShepherdInitTest()
    {
        Hound houndContext = Sheepehound.getHound();
        Unpacker mvcUnpacker = houndContext.getSheep("mvc", Unpacker.class);
    }
}
