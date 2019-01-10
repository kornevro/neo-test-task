package com.neotech.context;

import java.util.HashMap;
import java.util.Map;

/*
*       Мапа для хранения объектов и манипуляции над ними
* */

public final class TestContext {

    private static final ThreadLocal<Map<String, Object>> CONTEXT = new ThreadLocal<Map<String, Object>>() {
        @Override
        protected Map<String, Object> initialValue() {
            return new HashMap<>();
        }
    };

    private TestContext() {
    }

    public static Object getContextVariable(final String key) {
        return CONTEXT.get().get(key);
    }

    public static void put(final String key, final Object value) {
        CONTEXT.get().put(key, value);
    }

}
