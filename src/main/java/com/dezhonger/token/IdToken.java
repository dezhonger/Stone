package com.dezhonger.token;

/**
 * Created by dezhonger on 2019/6/25
 */
public class IdToken extends Token {

    private String identifier;

    public IdToken(int line, String identifier) {
        super(line);
        this.identifier = identifier;
    }

    @Override
    public boolean isIdentifier() {
        return true;
    }

    @Override
    public String getText() {
        return this.identifier;
    }
}