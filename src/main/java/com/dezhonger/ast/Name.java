package com.dezhonger.ast;

import com.dezhonger.token.Token;

/**
 * Created by dezhonger on 2019/6/25
 */
public class Name extends ASTLeaf {
    public Name(Token token) {
        super(token);
    }

    public String name() {
        return token.getText();
    }
}
