package com.dezhonger.runner;

import com.dezhonger.Lexer;
import com.dezhonger.exception.ParseException;
import com.dezhonger.token.Token;
import com.dezhonger.ui.CodeDialog;

/**
 * Created by dezhonger on 2019/6/25
 */



public class LexerRunner {
    public static void main(String[] args) throws ParseException {
        Lexer l = new Lexer(new CodeDialog());
        int count = 0;
        for (Token t; (t = l.read()) != Token.EOF; ) {
            System.out.println("token" + count++ +" "+ t.getText());
        }
    }
}
