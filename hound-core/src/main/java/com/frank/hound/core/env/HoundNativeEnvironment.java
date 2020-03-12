package com.frank.hound.core.env;

import com.frank.hound.core.support.HoundInitException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Hound 原生配置信息，通过指定规则设置Hound基本配置，比如基础包名等
 *
 * @author frank
 */
public class HoundNativeEnvironment extends HoundEnvironmentDecorator {

    /**
     * Hound原生配置文件位置
     */
    private static final String DEFAULT_ENV_CONFIG_FILE_PATH = "/HOUND-INF/hound.properties";

    private volatile AtomicBoolean isInitialized = new AtomicBoolean(false);

    public HoundNativeEnvironment(HoundConfigurableEnvironment houndEnv) {
        super(houndEnv);
    }

    @Override
    public void refresh() {

        // 初始化原生配置信息
        if (!isInitialized.get()) {
            // 读取原生配置
            init();

            isInitialized.set(true);
        }

        super.refresh();
    }

    /**
     * 获取原生配置
     */
    private void init() {
        Properties properties = new Properties();
        InputStream reader = getClass().getResourceAsStream(DEFAULT_ENV_CONFIG_FILE_PATH);
        try {
            properties.load(reader);
        } catch (IOException e) {
            // do nothing
            throw new HoundInitException(getClass(),"Environment load properties fail",e);
        }

        Set<String> innerPropertyKeysSet = properties.stringPropertyNames();
        for (String innerPropertyKey : innerPropertyKeysSet) {

            if(innerPropertyKey.startsWith("default.")) {
                setDefaultProperty(innerPropertyKey, properties.getProperty(innerPropertyKey));
            }
            else {
                setProperty(innerPropertyKey, properties.getProperty(innerPropertyKey));
            }
        }
    }
//
//    /**
//     * 检查获取的原生配置是否合法
//     * @return
//     */
//    private void check() {
//
//    }
}
