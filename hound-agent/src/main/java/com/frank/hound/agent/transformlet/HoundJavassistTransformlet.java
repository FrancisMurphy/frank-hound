package com.frank.hound.agent.transformlet;

import com.alibaba.ttl.internal.javassist.CannotCompileException;
import com.alibaba.ttl.internal.javassist.NotFoundException;
import com.alibaba.ttl.threadpool.agent.internal.transformlet.ClassInfo;

import java.io.IOException;

public interface HoundJavassistTransformlet
{
    void doTransform(ClassInfo classInfo) throws IOException,
            NotFoundException, CannotCompileException;

}
