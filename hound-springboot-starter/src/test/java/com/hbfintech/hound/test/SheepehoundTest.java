package com.hbfintech.hound.test;

import com.hbfintech.hound.core.acceptor.unpacker.Unpacker;
import com.hbfintech.hound.core.support.Hound;
import com.hbfintech.hound.core.support.Sheepehound;
import org.junit.Test;

public class SheepehoundTest
{
    @Test
    public void houndShepherdInitTest()
    {
        Hound houndContext = Sheepehound.getContext();
        Unpacker mvcUnpacker = houndContext.getSheep("mvc", Unpacker.class);

        Object mvcFilter = houndContext.getBridge("mvc");
        mvcFilter.toString();

        houndContext.sort();
    }
}
