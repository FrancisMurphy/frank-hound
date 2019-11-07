package com.hbfintech.hound.core;

import com.hbfintech.hound.core.acceptor.unpacker.Unpacker;
import com.hbfintech.hound.core.support.HoundContext;
import com.hbfintech.hound.core.support.HoundShepherd;
import org.junit.Test;

public class HoundShepherdTest
{
    @Test
    public void houndShepherdInitTest()
    {
        HoundContext houndContext = HoundShepherd.getContext();
        Unpacker mvcUnpacker = houndContext.getComponent("mvc", Unpacker.class);
    }
}
