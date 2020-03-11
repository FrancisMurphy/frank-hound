package com.frank.hound.core.common;

import com.frank.hound.core.constant.HoundConfigConstants;
import com.frank.hound.core.env.HoundEnvironment;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 管理包名的基础注册器
 * @author frank
 */
public abstract class BasePkgRegistry implements Refreshable
{
    /**
     *  配置key
     */
    private final String configKey;

    /**
     *  需要扫码的包名
     */
    private Set<String> pkgs = new HashSet<>();

    protected BasePkgRegistry(String configKey) {
        this.configKey = configKey;
    }

    @Override
    public void refresh(HoundEnvironment env)
    {
        String configBasePkgProp = env.getActiveProperty(HoundConfigConstants.HOUND_PLUGIN_BASE_PKG);
        if(StringUtils.isEmpty(configBasePkgProp))
        {
            return;
        }
        String[] configBasePkgPropArray = configBasePkgProp.split(",");
        pkgs = new HashSet<>(Arrays.asList(configBasePkgPropArray));
    }

    public Iterator<String> getPkgs() {
        return pkgs.iterator();
    }

}
