package com.frank.hound.core.common;

import com.frank.hound.core.env.HoundEnvironment;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public abstract class BasePkgRegistry implements Refreshable
{

    protected static final String DEFAULT_BASE_PKG = "com.hbfintech.hound";

    protected Set<String> configBasePkg = new HashSet<>();

    private static final String BASE_PKG_PROP_KEY = "hound.base.pkg";

    @Override
    public void refresh(HoundEnvironment houndEnvironment)
    {
        String configBasePkgProp = houndEnvironment.getActiveProperty(BASE_PKG_PROP_KEY);
        if(StringUtils.isEmpty(configBasePkgProp))
        {
            return;
        }
        String[] configBasePkgPropArray = configBasePkgProp.split(",");
        configBasePkg = new HashSet<>(Arrays.asList(configBasePkgPropArray));
    }
}
