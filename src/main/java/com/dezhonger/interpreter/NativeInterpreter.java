package com.dezhonger.interpreter;

import com.dezhonger.environment.NestedEnv;
import com.dezhonger.exception.ParseException;
import com.dezhonger.parser.ClosureParser;
import com.dezhonger.tools.Natives;

/**
 * Created by dezhonger on 2019/7/1
 */
public class NativeInterpreter extends BasicInterpreter {
    public static void main(String[] args) throws ParseException {
        run(new ClosureParser(), new Natives().environment(new NestedEnv()));
    }
}
