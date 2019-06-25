package com.dezhonger.ast;

import com.dezhonger.token.Token;

/**
 * Created by dezhonger on 2019/6/25
 */
public class StringLiteral extends ASTLeaf {
    public StringLiteral(Token token) {
        super(token);
    }

    public String value() {
        return token().getText();
    }
}

