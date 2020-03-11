package com.frank.hound.core.support;

import com.frank.hound.core.common.BasePkgRegistry;
import com.frank.hound.core.common.Closeable;
import com.frank.hound.core.constant.HoundConfigConstants;
import com.frank.hound.core.env.HoundEnvironment;
import lombok.NonNull;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 桥接器管理器
 * @author frank
 */
public class BridgeRegistry extends BasePkgRegistry implements Closeable {
    /**
     * hound bridge mapper {@link com.frank.hound.core.support.HoundBridge}
     */
    private Map<String, Object> bridgeMapper = new HashMap<>();

    BridgeRegistry() {
        super(HoundConfigConstants.HOUND_PLUGIN_BASE_PKG);
    }

    public void init(@NonNull HoundEnvironment env) {
        //刷新配置
        refresh(env);

        this.bridgeMapper = getTargetHoundBridge();
    }

    private Map<String, Object> getTargetHoundBridge() {

        Map<String, Object> targetBriageClazzMap = new HashMap<>(16);

        Iterator<String> basePkgIterator = getPkgs();
        while(basePkgIterator.hasNext()){
            targetBriageClazzMap.putAll(scanSpecifyPkgBridge(basePkgIterator.next()));
        }

        return targetBriageClazzMap;
    }

    private Map<String, Object> scanSpecifyPkgBridge(@NonNull String pkgPrefix) {
        Reflections reflections = new Reflections(pkgPrefix);

        Set<Class<?>> targetBridgeClazzSet = reflections.getTypesAnnotatedWith(HoundBridge.class);
        Map<String, Object> targetBridgeClazzMap = new HashMap<>(targetBridgeClazzSet.size());
        for (Class targetBridgelazz : targetBridgeClazzSet) {
            try {
                // TODO:将实例化移入Instance Factory
                targetBridgeClazzMap.put(((HoundBridge)targetBridgelazz.getAnnotation(HoundBridge.class)).value(),
                    targetBridgelazz.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                // do nothing
            }
        }
        return targetBridgeClazzMap;
    }

    public Iterator<Map.Entry<String, Object>> getBridges() {
        return bridgeMapper.entrySet().iterator();
    }

    public Object getBridge(@NonNull String bridgeName) {
        return bridgeMapper.get(bridgeName);
    }

    @Override
    public void close() {
        bridgeMapper.clear();
    }
}
