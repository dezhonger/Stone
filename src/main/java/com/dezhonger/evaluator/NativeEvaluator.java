package com.dezhonger.evaluator;

import com.dezhonger.ast.ASTree;
import com.dezhonger.environment.Environment;
import com.dezhonger.exception.StoneException;
import com.dezhonger.model.NativeFunction;
import javassist.gluonj.Require;
import javassist.gluonj.Reviser;

import java.util.List;

/**
 * Created by dezhonger on 2019/7/1
 * 原生函数调用
 */
@Require(FuncEvaluator.class)
@Reviser
public class NativeEvaluator {
    @Reviser
    public static class NativeArgEx extends FuncEvaluator.ArgumentsEx {
        public NativeArgEx(List<ASTree> children) {
            super(children);
        }

        @Override
        public Object eval(Environment callerEnv, Object value) {
            if (!(value instanceof NativeFunction)) {
                return super.eval(callerEnv, value);
            }

            NativeFunction func = (NativeFunction) value;
            int nparams = func.numOfParameters();
            if (size() != nparams) {
                throw new StoneException("bad number of arguments", this);
            }

            Object[] args = new Object[nparams];
            int num = 0;
            for (ASTree a : this) {
                BasicEvaluator.ASTreeEx ae = (BasicEvaluator.ASTreeEx) a;
                args[num++] = ae.eval(callerEnv);
            }
            return func.invoke(args, this);
        }
    }
}
