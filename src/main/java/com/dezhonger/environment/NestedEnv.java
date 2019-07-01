package com.dezhonger.environment;

import com.dezhonger.evaluator.FuncEvaluator;

import java.util.HashMap;

/**
 * Created by dezhonger on 2019/7/1
 */
public class NestedEnv implements Environment {
    protected HashMap<String, Object> values;
    protected Environment outer;

    public NestedEnv() {
        this(null);
    }

    public NestedEnv(Environment environment) {
        values = new HashMap<>();
        outer = environment;
    }

    public void setOuter(Environment outer) {
        this.outer = outer;
    }

    public void putNew(String name, Object value) {
        values.put(name, value);
    }

    public Environment where(String name) {
        if (values.get(name) != null) {
            return this;
        } else if (outer == null) {
            return null;
        } else {
            return ((FuncEvaluator.EnvEx) outer).where(name);
        }
    }

    @Override
    public void put(String name, Object value) {
        Environment e = where(name);
        if (e == null) {
            e = this;
        }
        ((FuncEvaluator.EnvEx) e).putNew(name, value);
    }

    @Override
    public Object get(String name) {
        Object v = values.get(name);
        if (v == null && outer != null) {
            return outer.get(name);
        } else {
            return v;
        }
    }
}
