package com.hbfintech.hound.test;

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

        Object mvcFilter = houndContext.getBridge("mvc");
        mvcFilter.toString();

        houndContext.sort();
    }
}
