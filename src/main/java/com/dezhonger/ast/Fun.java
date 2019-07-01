package com.dezhonger.ast;

import java.util.List;

/**
 * Created by dezhonger on 2019/7/1
 * 用于闭包的抽象语法树的节点
 */
public class Fun extends ASTList {
    public Fun(List<ASTree> children) {
        super(children);
    }

    public ParameterList parameters() {
        return (ParameterList) child(0);
    }

    public BlockStmnt body() {
        return (BlockStmnt) child(1);
    }

    @Override
    public String toString() {
        return "(fun " + parameters() + " " + body() + ")";
    }
}
