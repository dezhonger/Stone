package com.dezhonger.interpreter;

import com.dezhonger.environment.NestedEnv;
import com.dezhonger.exception.ParseException;
import com.dezhonger.parser.ClosureParser;

/**
 * Created by dezhonger on 2019/7/1
 * stone新添加的函数通过Function来实现
 */
public class ClosureInterpreter extends BasicInterpreter {
    public static void main(String[] args) throws ParseException {
        run(new ClosureParser(), new NestedEnv());
    }
}
