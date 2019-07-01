package com.dezhonger.runner;

import com.dezhonger.evaluator.ClosureEvaluator;
import com.dezhonger.interpreter.ClosureInterpreter;
import javassist.gluonj.util.Loader;

/**
 * Created by dezhonger on 2019/7/1
 */
public class ClosureRunner {
    public static void main(String[] args) throws Throwable {
        Loader.run(ClosureInterpreter.class, args, ClosureEvaluator.class);
    }
}
