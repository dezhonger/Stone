package com.dezhonger.ast;

import com.dezhonger.token.Token;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by dezhonger on 2019/6/25
 */
public class ASTLeaf extends ASTree {
    private static ArrayList<ASTree> empty = new ArrayList<>();

    protected Token token;

    public ASTLeaf(Token token) {
        this.token = token;
    }

    @Override
    public ASTree child(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override
    public int numChildren() {
        return 0;
    }

    @Override
    public Iterator<ASTree> children() {
        return empty.iterator();
    }

    @Override
    public String toString() {
        return this.token.getText();
    }

    @Override
    public String location() {
        return "at line " + this.token.getLineNumber();
    }

    public Token token() {
        return this.token;
    }
}
