package com.dezhonger.evaluator;

import com.dezhonger.ast.ASTLeaf;
import com.dezhonger.ast.ASTList;
import com.dezhonger.ast.ASTree;
import com.dezhonger.ast.BinaryExpr;
import com.dezhonger.ast.BlockStmnt;
import com.dezhonger.ast.ForStmnt;
import com.dezhonger.ast.IfStmnt;
import com.dezhonger.ast.Name;
import com.dezhonger.ast.NegativeExpr;
import com.dezhonger.ast.NullStmnt;
import com.dezhonger.ast.NumberLiteral;
import com.dezhonger.ast.StringLiteral;
import com.dezhonger.ast.WhileStmnt;
import com.dezhonger.environment.Environment;
import com.dezhonger.exception.StoneException;
import com.dezhonger.token.Token;
import javassist.gluonj.Reviser;

import java.util.List;

/**
 * Created by dezhonger on 2019/6/26
 *
 * 计算ast表达式
 */
@Reviser
public class BasicEvaluator {
    public static final int TRUE = 1;
    public static final int FALSE = 0;

    @Reviser
    public static abstract class ASTreeEx extends ASTree {
        public abstract Object eval(Environment environment);
    }

    @Reviser
    public static class ASTListEx extends ASTList {
        public ASTListEx(List<ASTree> children) {
            super(children);
        }

        public Object eval(Environment environment) {
            throw new StoneException("cannot eval: " + toString(), this);
        }
    }

    @Reviser
    public static class ASTLeafEx extends ASTLeaf {
        public ASTLeafEx(Token token) {
            super(token);
        }

        public Object eval(Environment environment) {
            throw new StoneException("cannot eval:" + toString(), this);
        }
    }

    @Reviser
    public static class NumberEx extends NumberLiteral {
        public NumberEx(Token token) {
            super(token);
        }

        public Object eval(Environment environment) {
            return value();
        }
    }

    @Reviser
    public static class StringEx extends StringLiteral {
        public StringEx(Token token) {
            super(token);
        }

        public Object eval(Environment environment) {
            return value();
        }
    }

    @Reviser
    public static class NameEx extends Name {
        public NameEx(Token token) {
            super(token);
        }

        public Object eval(Environment environment) {
            Object value = environment.get(name());
            if (value == null) {
                throw new StoneException("undefined name: " + name(), this);
            } else {
                return value;
            }
        }
    }

    @Reviser
    public static class NegativeEx extends NegativeExpr {
        public NegativeEx(List<ASTree> children) {
            super(children);
        }

        public Object eval(Environment environment) {
            Object value = ((ASTreeEx) operand()).eval(environment);
            if (value instanceof Integer) {
                return -(Integer) value;
            } else {
                throw new StoneException("bad type for -", this);
            }
        }
    }

    @Reviser
    public static class BinaryEx extends BinaryExpr {
        public BinaryEx(List<ASTree> children) {
            super(children);
        }

        public Object eval(Environment environment) {
            String op = operator();
            if ("=".equals(op)) {
                Object right = ((ASTreeEx) right()).eval(environment);
                return computeAssign(environment, right);
            } else {
                Object left = ((ASTreeEx) left()).eval(environment);
                Object right = ((ASTreeEx) right()).eval(environment);
                return computeOp(left, op, right);
            }
        }

        protected Object computeAssign(Environment environment, Object rvalue) {
            ASTree l = left();
            if (l instanceof Name) {
                environment.put(((Name) l).name(), rvalue);
                return rvalue;
            } else {
                throw new StoneException("bad assignment", this);
            }
        }

        protected Object computeOp(Object left, String op, Object right) {
            if (left instanceof Integer && right instanceof Integer) {
                return computeNumber((Integer) left, op, (Integer) right);
            } else {
                if (op.equals("+")) {
                    return String.valueOf(left) + String.valueOf(right);
                } else if (op.equals("==")) {
                    if (left == null) {
                        return right == null ? TRUE : FALSE;
                    } else {
                        return left.equals(right) ? TRUE : FALSE;
                    }
                } else {
                    throw new StoneException("bad type", this);
                }
            }
        }

        protected Object computeNumber(Integer left, String op, Integer right) {
            int a = left;
            int b = right;

            if (op.equals("+")) {
                return a + b;
            } else if (op.equals("-")) {
                return a - b;
            } else if (op.equals("*")) {
                return a * b;
            } else if (op.equals("/")) {
                return a / b;
            } else if (op.equals("%")) {
                return a % b;
            } else if (op.equals("==")) {
                return a == b ? TRUE : FALSE;
            } else if (op.equals(">")) {
                return a > b ? TRUE : FALSE;
            } else if (op.equals("<")) {
                return a < b ? TRUE : FALSE;
            } else if (op.equals(">=")) {
                return a >= b ? TRUE : FALSE;
            } else if (op.equals("<=")) {
                return a <= b ? TRUE : FALSE;
            } else {
                throw new StoneException("bad operator", this);
            }
        }
    }

    @Reviser
    public static class BlockEx extends BlockStmnt {
        public BlockEx(List<ASTree> children) {
            super(children);
        }

        public Object eval(Environment environment) {
            Object result = 0;
            for (ASTree t : this) {
                if (!(t instanceof NullStmnt)) {
                    result = ((ASTreeEx) t).eval(environment);
                }
            }
            return result;
        }
    }

    @Reviser
    public static class IfEx extends IfStmnt {
        public IfEx(List<ASTree> children) {
            super(children);
        }

        public Object eval(Environment environment) {
            Object c = ((ASTreeEx) condition()).eval(environment);

            if (c instanceof Integer && (Integer) c != FALSE) {
                return ((ASTreeEx) thenBlock()).eval(environment);
            } else {
                ASTree b = elseBlock();
                if (b == null) {
                    return 0;
                } else {
                    return ((ASTreeEx) b).eval(environment);
                }
            }
        }
    }

    @Reviser
    public static class WhileEx extends WhileStmnt {
        public WhileEx(List<ASTree> children) {
            super(children);
        }

        public Object eval(Environment environment) {
            Object result = 0;
            for (; ; ) {
                Object c = ((ASTreeEx) condition()).eval(environment);
                if (c instanceof Integer && ((Integer) c) == FALSE) {
                    return result;
                } else {
                    result = ((ASTreeEx) body()).eval(environment);
                }
            }
        }
    }

    @Reviser
    public static class ForEx extends ForStmnt {
        public ForEx(List<ASTree> children) {
            super(children);
        }

        public Object eval(Environment environment) {
            Object result = 0;

            ((ASTreeEx) initial()).eval(environment);
            for (; ; ) {
                Object c = ((ASTreeEx) condition()).eval(environment);
                if (c instanceof Integer && ((Integer) c) == FALSE) {
                    return result;
                } else {
                    result = ((ASTreeEx) body()).eval(environment);
                    ((ASTreeEx) step()).eval(environment);
                }
            }
        }
    }
}
