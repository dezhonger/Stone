package com.dezhonger.evaluator;

import com.dezhonger.ast.ASTree;
import com.dezhonger.ast.Fun;
import com.dezhonger.environment.Environment;
import com.dezhonger.model.Function;
import javassist.gluonj.Require;
import javassist.gluonj.Reviser;

import java.util.List;

/**
 * Created by dezhonger on 2019/7/1
 */
@Require(FuncEvaluator.class)
@Reviser public class ClosureEvaluator {
    @Reviser
    public static class FunEx extends Fun {
        public FunEx(List<ASTree> children) {
            super(children);
        }

        public Object eval(Environment environment) {
            return new Function(parameters(), body(), environment);
        }
    }
}
