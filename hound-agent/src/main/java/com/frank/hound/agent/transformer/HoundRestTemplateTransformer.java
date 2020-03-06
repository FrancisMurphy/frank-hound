package com.frank.hound.agent.transformer;

import com.alibaba.ttl.internal.javassist.CannotCompileException;
import com.alibaba.ttl.internal.javassist.CtClass;
import com.alibaba.ttl.internal.javassist.CtMethod;
import com.alibaba.ttl.internal.javassist.NotFoundException;
import com.alibaba.ttl.threadpool.agent.internal.transformlet.ClassInfo;
import com.frank.hound.agent.transformlet.HoundJavassistTransformlet;

import java.io.IOException;

public class HoundRestTemplateTransformer implements HoundJavassistTransformlet
{

    private String HTTP_CLIENT_BUILDER_CLASS_NAME = "org.springframework.web.client.RestTemplate";

    @Override
    public void doTransform(ClassInfo classInfo)
            throws IOException, CannotCompileException
    {
        final CtClass clazz = classInfo.getCtClass();

        try
        {
            CtMethod buildMethod = clazz
                    .getDeclaredMethod(HTTP_CLIENT_BUILDER_CLASS_NAME);

            String code = "boolean isHoundInterceptorInit = false;" +
                    "      for(org.springframework.http.client.ClientHttpRequestInterceptor restTemplateInterceptor : restTemplate.getInterceptors())" +
                    "      {if(restTemplateInterceptor instanceof com.frank.hound.plugin.spring.resttemplate.HoundRestTemplateInterceptor)" +
                    "         {isHoundInterceptorInit = true;}}" +
                    "        if(!isHoundInterceptorInit){" +
                    "            com.frank.hound.core.support.Hound houndContext = com.frank.hound.core.support.Sheepehound.getHound();" +
                    "            if(houndContext!=null){" +
                    "                com.frank.hound.plugin.spring.resttemplate.HoundRestTemplateInterceptor houndRestTemplateInterceptor =" +
                    "                        (com.frank.hound.plugin.spring.resttemplate.HoundRestTemplateInterceptor) houndContext.getBridge(\"restTemplate\");" +
                    "                if(houndRestTemplateInterceptor!=null){" +
                    "                    restTemplate.getInterceptors().add(houndRestTemplateInterceptor);}}}";
            buildMethod.insertBefore(code);

        }
        catch (NotFoundException e)
        {
            // clazz does not override build method, do nothing.
        }
    }
}
