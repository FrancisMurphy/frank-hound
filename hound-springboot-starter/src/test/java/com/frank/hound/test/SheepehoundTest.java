package com.frank.hound.test;

import com.frank.hound.core.acceptor.unpacker.Unpacker;
import com.frank.hound.core.support.Hound;
import com.frank.hound.core.support.SheepeHound;
import org.junit.Test;

public class SheepehoundTest
{
    @Test
    public void houndShepherdInitTest()
    {
        Hound houndContext = SheepeHound.getHound();
        Unpacker mvcUnpacker = houndContext.getSheep("mvc", Unpacker.class);

        Object mvcFilter = houndContext.getBridge("mvc");
        mvcFilter.toString();

        houndContext.sort();
    }


}
