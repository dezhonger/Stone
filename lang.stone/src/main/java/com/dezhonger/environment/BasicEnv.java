package com.dezhonger.environment;

import java.util.HashMap;

/**
 * Created by dezhonger on 2019/6/26
 *
 * 存储变量对应的值
 */
public class BasicEnv implements Environment {
    protected HashMap<String, Object> values;

    public BasicEnv() {
        values = new HashMap<>();
    }

    @Override
    public void put(String name, Object value) {
        values.put(name, value);
    }

    @Override
    public Object get(String name) {
        return values.get(name);
    }
}
