package com.dezhonger.ast;

import java.util.List;

/**
 * Created by dezhonger on 2019/7/1
 */
public class ParameterList extends ASTList{

    public ParameterList(List<ASTree> children) {
        super(children);
    }

    public String name(int i) {
        return ((ASTLeaf) child(i)).token().getText();
    }

    public int size() {
        return numChildren();
    }

}
