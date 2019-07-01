package com.dezhonger.runner;

import com.dezhonger.evaluator.ClosureEvaluator;
import com.dezhonger.evaluator.NativeEvaluator;
import com.dezhonger.interpreter.NativeInterpreter;
import javassist.gluonj.util.Loader;

/**
 * Created by dezhonger on 2019/7/1
 */
public class NativeRunner {
    public static void main(String[] args) throws Throwable {
        Loader.run(NativeInterpreter.class, args, NativeEvaluator.class, ClosureEvaluator.class);
    }
}
