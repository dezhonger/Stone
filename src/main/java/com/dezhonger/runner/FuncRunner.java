package com.dezhonger.runner;

import com.dezhonger.evaluator.FuncEvaluator;
import com.dezhonger.interpreter.FuncInterpreter;
import javassist.gluonj.util.Loader;

/**
 * Created by dezhonger on 2019/7/1
 */
public class FuncRunner {
    public static void main(String[] args) throws Throwable {
        Loader.run(FuncInterpreter.class, args, FuncEvaluator.class);

    }
}
