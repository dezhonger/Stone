package com.dezhonger.parser;

import com.dezhonger.ast.Fun;

import static com.dezhonger.parser.Parser.rule;

/**
 * Created by dezhonger on 2019/7/1
 * 支持闭包的语法分析器
 */
public class ClosureParser extends FuncParser {
    public ClosureParser() {
        primary.insertChoice(rule(Fun.class).sep("fun").ast(paramList).ast(block));
    }
}
