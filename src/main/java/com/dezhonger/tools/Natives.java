package com.dezhonger.tools;

import com.dezhonger.environment.Environment;
import com.dezhonger.exception.StoneException;
import com.dezhonger.model.NativeFunction;

import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * Created by dezhonger on 2019/7/1
 */
public class Natives {
    public Environment environment(Environment environment) {
        appendNatives(environment);
        return environment;
    }

    protected void appendNatives(Environment environment) {
        append(environment, "print", Natives.class, "print", Object.class);
        append(environment, "read", Natives.class, "read");
        append(environment, "length", Natives.class, "length", String.class);
        append(environment, "toInt", Natives.class, "toInt", Object.class);
        append(environment, "currentTime", Natives.class, "currentTime");
    }

    protected void append(Environment environment, String name, Class<?> clazz, String methodName, Class<?>... params) {
        Method method;
        try {
            method = clazz.getMethod(methodName, params);
        } catch (Exception e) {
            throw new StoneException("cannot find a native function: " + methodName);
        }
        environment.put(name, new NativeFunction(methodName, method));
    }

    public static int print(Object object) {
        System.out.println(object.toString());
        return 0;
    }

    public static String read() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    public static int length(String s) {
        return s.length();
    }

    public static int toInt(Object value) {
        if (value instanceof String) {
            return Integer.parseInt((String) value);
        } else if (value instanceof Integer) {
            return (Integer) value;
        } else {
            throw new NumberFormatException(value.toString());
        }
    }

    public static int currentTime() {
        return (int) System.currentTimeMillis();
    }
}
