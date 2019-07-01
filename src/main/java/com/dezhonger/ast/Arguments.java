package com.dezhonger.ast;

import java.util.List;

/**
 * Created by dezhonger on 2019/7/1
 */
public class Arguments extends Postfix {
    public Arguments(List<ASTree> children) {
        super(children);
    }

    public int size() {
        return numChildren();
    }
}
