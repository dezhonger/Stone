package com.dezhonger.runner;

import com.dezhonger.evaluator.BasicEvaluator;
import com.dezhonger.interpreter.BasicInterpreter;
import javassist.gluonj.util.Loader;

/**
 * Created by dezhonger on 2019/6/26
 * 解释器执行程序
 */
public class Runner {
    public static void main(String[] args) throws Throwable {
        Loader.run(BasicInterpreter.class, args, BasicEvaluator.class);
    }
}
