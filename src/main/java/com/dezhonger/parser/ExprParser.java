package com.dezhonger.parser;

import com.dezhonger.Lexer;
import com.dezhonger.ast.ASTLeaf;
import com.dezhonger.ast.ASTree;
import com.dezhonger.ast.BinaryExpr;
import com.dezhonger.ast.NumberLiteral;
import com.dezhonger.exception.ParseException;
import com.dezhonger.token.Token;
import com.dezhonger.ui.CodeDialog;

import java.util.Arrays;

/**
 * Created by dezhonger on 2019/7/1
 *
 * LL语法分析的递归下降语法分析器
 * 四则运算表达式的语法规则
 *
 * digit: "0" | "1" | "2" .... | "8" | "9"
 * number: digit | digit number
 * factor: number | "(" expression ")"
 * term: factor { ("*" | "/") factor }
 * expression: term { ("+" | "-" ) term }
 *
 */

public class ExprParser {
    private Lexer lexer;
    public ExprParser(Lexer p) {
        this.lexer = p;
    }

    public ASTree expression() throws ParseException {
        ASTree left = term();
        while (isToken("+") | isToken("-")) {
            ASTLeaf op = new ASTLeaf(lexer.read());
            ASTree right = term();
            left = new BinaryExpr(Arrays.asList(left, op, right));
        }
        return left;
    }

    public ASTree term() throws ParseException {
        ASTree left = factor();
        while (isToken("*") | isToken("/")) {
            ASTLeaf op = new ASTLeaf(lexer.read());
            ASTree right = term();
            left = new BinaryExpr(Arrays.asList(left, op, right));
        }
        return left;
    }

    public ASTree factor() throws ParseException {
        if (isToken("(")) {
            token("(");
            ASTree e = expression();
            token(")");
            return e;
        } else {
            Token t = lexer.read();
            if (t.isNumber()) {
                NumberLiteral n = new NumberLiteral(t);
                return n;
            } else {
                throw new ParseException(t);
            }
        }
    }

    void token(String name) throws ParseException {
        Token t = lexer.read();
        if (!(t.isIdentifier() && name.equals(t.getText()))) {
            throw new ParseException(t);
        }

    }

    boolean isToken(String name) throws ParseException {
        Token t = lexer.peek(0);
        return t.isIdentifier() && name.equals(t.getText());
    }

    public static void main(String[] args) throws ParseException {
        Lexer lexer = new Lexer(new CodeDialog());
        ExprParser p = new ExprParser(lexer);
        ASTree t = p.expression();
        System.out.println("=> " + t);
    }
}
