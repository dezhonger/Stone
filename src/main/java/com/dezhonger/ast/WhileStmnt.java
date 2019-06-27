package com.dezhonger.ast;

import java.util.List;

/**
 * Created by dezhonger on 2019/6/25
 */
public class WhileStmnt extends ASTList {
    public WhileStmnt(List<ASTree> children) {
        super(children);
    }

    public ASTree condition() {
        return child(0);
    }

    public ASTree body() {
        return child(1);
    }

    @Override
    public String toString() {
        return "(while " + condition() + " " + body() + ")";
    }
}
