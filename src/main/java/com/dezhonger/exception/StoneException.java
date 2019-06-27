package com.dezhonger.exception;

import com.dezhonger.ast.ASTree;

/**
 * Created by dezhonger on 2019/6/25
 */
public class StoneException extends RuntimeException {
    public StoneException(String m) {
        super(m);
    }
    public StoneException(String message, ASTree tree) {
        super(message + " " + tree.location());
    }
}
