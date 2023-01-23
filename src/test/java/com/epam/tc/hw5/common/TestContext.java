package com.epam.tc.hw5.common;

import java.util.HashMap;
import java.util.Map;

public class TestContext {

    private Map<String, Object> context = new HashMap<>();
    private static TestContext instance;

    private TestContext() {}

    public static TestContext getInstance() {
        if (instance == null) {
            instance = new TestContext();
        }
        return instance;
    }

    public void set(String key, Object value) {
        context.put(key, value);
    }

    public <T> T get(String key, Class<T> clazz) {
        return clazz.cast(context.get(key));
    }

    public void clear() {
        context.clear();
    }
}
