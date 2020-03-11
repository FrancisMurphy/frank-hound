package com.frank.hound.core.common;

import com.frank.hound.core.constant.HoundConfigConstants;
import com.frank.hound.core.env.HoundEnvironment;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public abstract class BasePkgRegistry implements Refreshable
{
    /**
     *  需要扫码的包名
     */
    @Getter
    private Set<String> basePkg = new HashSet<>();

    private HoundEnvironment houndEnvironment;

    public BasePkgRegistry(HoundEnvironment houndEnvironment) {
        this.houndEnvironment = houndEnvironment;
    }

    @Override
    public void refresh()
    {
        String configBasePkgProp = houndEnvironment.getActiveProperty(HoundConfigConstants.HOUND_PLUGIN_BASE_PKG);
        if(StringUtils.isEmpty(configBasePkgProp))
        {
            return;
        }
        String[] configBasePkgPropArray = configBasePkgProp.split(",");
        basePkg = new HashSet<>(Arrays.asList(configBasePkgPropArray));
    }


}
