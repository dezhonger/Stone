package com.dezhonger.ast;

import java.util.Iterator;

/**
 * Created by dezhonger on 2019/6/25
 */
public abstract class ASTree implements Iterable<ASTree> {
    /**
     * 第i个子节点
     * @param i
     * @return
     */
    public abstract ASTree child(int i);


    /**
     * 子节点的个数
     * @return
     */
    public abstract int numChildren();

    public abstract Iterator<ASTree> children();

    public abstract String location();

    /**
     * 返回一个用于遍历子节点的iterator
     * @return
     */
    public Iterator<ASTree> iterator() {
        return children();
    }
}
