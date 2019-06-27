package com.dezhonger.runner;

import com.dezhonger.Lexer;
import com.dezhonger.ast.ASTree;
import com.dezhonger.exception.ParseException;
import com.dezhonger.parser.BasicParser;
import com.dezhonger.token.Token;
import com.dezhonger.ui.CodeDialog;

/**
 * Created by dezhonger on 2019/6/25
 */
public class ParserRunner {
    public static void main(String[] args) throws ParseException {
        Lexer l = new Lexer(new CodeDialog());
        BasicParser basicParser = new BasicParser();
        while (l.peek(0) != Token.EOF) {
            ASTree ast = basicParser.parse(l);
            System.out.println("=> " + ast.toString());
        }
    }
}
