package com.dezhonger.parser;

import com.dezhonger.ast.Arguments;
import com.dezhonger.ast.DefStmnt;
import com.dezhonger.ast.ParameterList;

import static com.dezhonger.parser.Parser.rule;

/**
 * Created by dezhonger on 2019/7/1
 *
 * 支持函数功能的语法分析器
 */
public class FuncParser extends BasicParser {
    Parser param = rule().identifier(reserved);
    Parser params = rule(ParameterList.class).ast(param).repeat(rule().sep(",").ast(param));
    Parser paramList = rule().sep("(").maybe(params).sep(")");
    Parser def = rule(DefStmnt.class).sep("def").identifier(reserved).ast(paramList).ast(block);
    Parser args = rule(Arguments.class).ast(expr).repeat(rule().sep(",").ast(expr));
    Parser postfix = rule().sep("(").maybe(args).sep(")");

    public FuncParser() {
        reserved.add(")");
        primary.repeat(postfix);
        simple.option(args);
        program.insertChoice(def);
    }
}
