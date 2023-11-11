package org.embeddedt.reflectionstestmod;

import com.mojang.logging.LogUtils;
import net.fabricmc.api.ModInitializer;
import org.embeddedt.reflectionstestmod.module.BaseModule;
import org.embeddedt.reflectionstestmod.module.Module;
import org.reflections.Reflections;
import org.slf4j.Logger;

public class Reflectionstestmod implements ModInitializer {
    private static final Logger LOGGER = LogUtils.getLogger();

    static {
        Reflections reflections = new Reflections(BaseModule.class.getPackageName());
        for (var MODULE_TYPE : reflections.getTypesAnnotatedWith(Module.class)) {
            if (BaseModule.class.isAssignableFrom(MODULE_TYPE)) {
                LOGGER.info("Found module {}", MODULE_TYPE.getTypeName());
            } else {
                LOGGER.error("Class {} is marked @Module but does not extend {}.", MODULE_TYPE.getTypeName(), BaseModule.class.getTypeName());
            }
        }
    }

    @Override
    public void onInitialize() {

    }
}
