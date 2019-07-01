package com.dezhonger.model;

import com.dezhonger.ast.ASTree;
import com.dezhonger.exception.StoneException;

import java.lang.reflect.Method;

/**
 * Created by dezhonger on 2019/7/1
 */
public class NativeFunction {
    protected Method method;
    protected String name;
    protected int numParams;

    public NativeFunction(String name, Method method) {
        this.name = name;
        this.method = method;
        numParams = method.getParameterTypes().length;
    }

    @Override
    public String toString() {
        return "<native:" + hashCode() + ">";
    }

    public int numOfParameters() {
        return numParams;
    }

    public Object invoke(Object[] args, ASTree tree) {
        try {
            return method.invoke(null, args);
        } catch (Exception e) {
            throw new StoneException("bad native funtion call:" + name, tree);
        }
    }
}
