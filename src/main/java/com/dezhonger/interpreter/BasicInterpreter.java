package com.dezhonger.interpreter;


import com.dezhonger.Lexer;
import com.dezhonger.ast.ASTree;
import com.dezhonger.ast.NullStmnt;
import com.dezhonger.environment.BasicEnv;
import com.dezhonger.environment.Environment;
import com.dezhonger.evaluator.BasicEvaluator;
import com.dezhonger.exception.ParseException;
import com.dezhonger.parser.BasicParser;
import com.dezhonger.token.Token;
import com.dezhonger.ui.CodeDialog;

/**
 *
 * Basic interpreter 解释器
 * Created by dezhonger on 2019/6/25
 */
public class BasicInterpreter {
    public static void main(String[] args) throws ParseException {
        run(new BasicParser(), new BasicEnv());
    }

    public static void run(BasicParser basicParser, Environment environment) throws ParseException {
        Lexer lexer = new Lexer(new CodeDialog());
        while (lexer.peek(0) != Token.EOF) {
            ASTree asTree = basicParser.parse(lexer);
            if (!(asTree instanceof NullStmnt)) {
                Object r = ((BasicEvaluator.ASTreeEx) asTree).eval(environment);
                System.out.println("=> " + r);
            }
        }
    }
}
