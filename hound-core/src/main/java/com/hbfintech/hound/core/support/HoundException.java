package com.hbfintech.hound.core.support;

public class HoundException extends RuntimeException
{
    private String houndExceptionMsg;

    public HoundException(String houndExceptionMsg)
    {
        super(houndExceptionMsg);
        this.houndExceptionMsg = "HoundException:" + houndExceptionMsg;
    }
}
