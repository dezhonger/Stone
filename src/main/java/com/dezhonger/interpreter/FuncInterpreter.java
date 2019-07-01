package com.dezhonger.interpreter;

import com.dezhonger.environment.NestedEnv;
import com.dezhonger.exception.ParseException;
import com.dezhonger.parser.FuncParser;

/**
 * Created by dezhonger on 2019/7/1
 */
public class FuncInterpreter extends BasicInterpreter {
    public static void main(String[] args) throws ParseException {
        run(new FuncParser(), new NestedEnv());
    }
}
