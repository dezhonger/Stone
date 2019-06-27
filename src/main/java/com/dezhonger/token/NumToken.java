package com.dezhonger.token;

/**
 * Created by dezhonger on 2019/6/25
 */
public class NumToken extends Token {

    private int number;

    public NumToken(int lineNo, int number) {
        super(lineNo);
        this.number = number;
    }

    @Override
    public boolean isNumber() {
        return true;
    }

    @Override
    public int getNumber() {
        return this.number;
    }

    @Override
    public String getText() {
        return Integer.toString(this.number);
    }
}
