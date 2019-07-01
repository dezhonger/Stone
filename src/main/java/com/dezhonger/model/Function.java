package com.dezhonger.model;


import com.dezhonger.ast.BlockStmnt;
import com.dezhonger.ast.ParameterList;
import com.dezhonger.environment.Environment;
import com.dezhonger.environment.NestedEnv;

public class Function {
    protected ParameterList parameters;
    protected BlockStmnt body;
    protected Environment environment;

    public Function(ParameterList parameters, BlockStmnt body, Environment environment) {
        this.parameters = parameters;
        this.body = body;
        this.environment = environment;
    }

    public ParameterList parameters() {
        return parameters;
    }

    public BlockStmnt body() {
        return body;
    }

    public Environment makeEnv() {
        return new NestedEnv(environment);
    }

    @Override
    public String toString() {
        return "<fun:" + hashCode() + ">";
    }
}
