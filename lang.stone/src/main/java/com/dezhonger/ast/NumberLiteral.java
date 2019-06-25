package com.dezhonger.ast;

import com.dezhonger.token.Token;

/**
 * Created by dezhonger on 2019/6/25
 */
public class NumberLiteral extends ASTLeaf {

    public NumberLiteral(Token token) {
        super(token);
    }

    public int value() {
        return token.getNumber();
    }
}

