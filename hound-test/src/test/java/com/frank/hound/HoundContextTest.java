package com.frank.hound;

import com.frank.hound.core.acceptor.unpacker.Unpacker;
import com.frank.hound.core.support.Hound;
import com.frank.hound.core.support.SheepeHound;

public class HoundContextTest {

    public static void houndContextInitTest()
    {
        Hound houndContext = SheepeHound.getHound();
        Unpacker mvcUnpacker = houndContext.getSheep("mvc", Unpacker.class);
    }

    public static void main(String[] args) {
        houndContextInitTest();
    }

}
