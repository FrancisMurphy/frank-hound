package com.hbfintech.hound.core.support;

public class HoundException extends RuntimeException
{
    private final String houndExceptionMsg;

    public HoundException(String houndExceptionMsg)
    {
        super(houndExceptionMsg);
        this.houndExceptionMsg = "HoundException:" + houndExceptionMsg;
    }
}
