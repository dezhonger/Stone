package com.dezhonger.ast;

import java.util.List;

/**
 * Created by dezhonger on 2019/7/1
 */
public class DefStmnt extends ASTList {
    public DefStmnt(List<ASTree> children) {
        super(children);
    }

    public String name() {
        return ((ASTLeaf) child(0)).token().getText();
    }

    public ParameterList parameters() {
        return (ParameterList) child(1);
    }

    public BlockStmnt body() {
        return (BlockStmnt) child(2);
    }

    @Override
    public String toString() {
        return "(def " + name() + " " + parameters() + " " + body() + ")";
    }
}
