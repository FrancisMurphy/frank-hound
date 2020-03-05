package com.frank.hound.agent.transformer;

import com.alibaba.ttl.threadpool.agent.internal.transformlet.ClassInfo;
import com.frank.hound.agent.transformlet.HoundJavassistTransformlet;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.List;

/**
 * Hound {@link ClassFileTransformer} of Java Agent
 * Support hound-agent expansion mechanism
 * The realization{@link HoundJavassistTransformlet} of specific function
 *
 * @author frank
 */
public class HoundTransformer implements ClassFileTransformer
{
    private static final byte[] EMPTY_BYTE_ARRAY = {};

    private final List<HoundJavassistTransformlet> transformletList = new ArrayList<>();

    public HoundTransformer(List<? extends HoundJavassistTransformlet> transformletList)
    {
        for (HoundJavassistTransformlet transformlet : transformletList) {
            this.transformletList.add(transformlet);
        }
    }

    @Override
    public byte[] transform(ClassLoader loader, String classFile,
            Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
            byte[] classFileBuffer) throws IllegalClassFormatException
    {
        try {
            // Lambda has no class file, no need to transform, just return.
            if (classFile == null)
            {
                return EMPTY_BYTE_ARRAY;
            }

            final String className = toClassName(classFile);

            ClassInfo classInfo = new ClassInfo(className, classFileBuffer, loader);

            for (HoundJavassistTransformlet transformlet : transformletList) {
                transformlet.doTransform(classInfo);
                if (classInfo.isModified())
                {
                    return classInfo.getCtClass().toBytecode();
                }
            }
        } catch (Throwable t) {
            String msg = "Fail to transform class " + classFile + ", cause: " + t.toString();
            throw new IllegalStateException(msg, t);
        }

        return EMPTY_BYTE_ARRAY;
    }

    private static String toClassName(final String classFile) {
        return classFile.replace('/', '.');
    }
}
