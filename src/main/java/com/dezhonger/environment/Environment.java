package com.dezhonger.environment;

/**
 * Created by dezhonger on 2019/6/26
 */
public interface Environment {
    void put(String name, Object value);

    Object get(String name);
}
