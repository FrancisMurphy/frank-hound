package com.frank.hound.core.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class AddressUtils {

    public static String getLocalIP() {
        InetAddress addr;
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            return null;
        }

        byte[] ipAddr = addr.getAddress();
        StringBuilder ipAddrStr = new StringBuilder();
        for (int i = 0; i < ipAddr.length; i++) {
            if (i > 0) {
                ipAddrStr.append(".");
            }
            ipAddrStr.append(ipAddr[i] & 0xFF);
        }
        return ipAddrStr.toString();
    }

}
