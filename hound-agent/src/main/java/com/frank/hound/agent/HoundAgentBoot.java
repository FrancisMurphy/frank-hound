package com.frank.hound.agent;

import com.alibaba.ttl.threadpool.agent.TtlAgent;
import com.frank.hound.agent.banner.BannerPrinter;
import com.frank.hound.agent.transformer.HoundHttpClientTransformer;
import com.frank.hound.agent.transformer.HoundRestTemplateTransformer;
import com.frank.hound.agent.transformer.HoundTransformer;
import com.frank.hound.agent.transformlet.HoundJavassistTransformlet;

import java.lang.instrument.Instrumentation;
import java.util.ArrayList;
import java.util.List;

public class HoundAgentBoot
{
    public static void premain(String agentArgs, Instrumentation inst)
    {
        BannerPrinter.printBanner();
        TtlAgent.premain(agentArgs, inst);

        //加载hound agent
        final List<HoundJavassistTransformlet> transformletList = new ArrayList<>();
        transformletList.add(new HoundHttpClientTransformer());
        transformletList.add(new HoundRestTemplateTransformer());
        inst.addTransformer(new HoundTransformer(transformletList));
    }

}
