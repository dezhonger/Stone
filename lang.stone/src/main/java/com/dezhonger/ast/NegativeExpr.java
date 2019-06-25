package com.dezhonger.ast;

import java.util.List;

/**
 * Created by dezhonger on 2019/6/25
 */
public class NegativeExpr extends ASTList {
    public NegativeExpr(List<ASTree> children) {
        super(children);
    }

    public ASTree operand() {
        return child(0);
    }

    @Override
    public String toString() {
        return "-" + operand();
    }
}

