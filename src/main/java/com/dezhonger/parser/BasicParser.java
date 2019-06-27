package com.dezhonger.parser;

import com.dezhonger.Lexer;
import com.dezhonger.ast.ASTree;
import com.dezhonger.ast.BinaryExpr;
import com.dezhonger.ast.BlockStmnt;
import com.dezhonger.ast.ForStmnt;
import com.dezhonger.ast.IfStmnt;
import com.dezhonger.ast.Name;
import com.dezhonger.ast.NegativeExpr;
import com.dezhonger.ast.NullStmnt;
import com.dezhonger.ast.NumberLiteral;
import com.dezhonger.ast.PrimaryExpr;
import com.dezhonger.ast.StringLiteral;
import com.dezhonger.ast.WhileStmnt;
import com.dezhonger.exception.ParseException;
import com.dezhonger.token.Token;

import java.util.HashSet;

import static com.dezhonger.parser.Parser.Operators;
import static com.dezhonger.parser.Parser.rule;

/**
 * Created by dezhonger on 2019/6/25
 * BasicParser是一个语法分析程序，定义了大量的Parser类型的字段，例如primary字段的定义基于非终结符primary的语法规则，factor和block同理，都是相应的java语言的语法规则
 *
 * 根据定义的Parser对象能够根据各种类型的非终结符模式来执行语法分析。
 * 例如：将此法分析器作为参数，调用program字段的parse方法，就能够从词法分析器获取一行程序中包含的单词，并对其做语法分析，返回一颗语法树。
 * 请注意一下，这是一个public方法，仅用于调用program字段的parse方法
 *
 */


public class BasicParser {
    /**
     * 包含的标识符无法作为变量名使用
     */
    HashSet<String> reserved = new HashSet<>();
    Parser.Operators operators = new Parser.Operators();



    Parser expr0 = rule();


    /**
     * primary:  "(" expr ")" | NUMBER | IDENTIFIER | STRING
     * 非终结符，用于表示括号括起的表达式、整型字面量、标识符（变量名）或字符串字面量
     */
    Parser primary = rule(PrimaryExpr.class).or(
            rule().sep("(").ast(expr0).sep(")"),
            rule().number(NumberLiteral.class),
            rule().identifier(Name.class, reserved),
            rule().string(StringLiteral.class));

    /**
     * factor: "-" primary | primary
     * 非终结符(因子)或表示一个 primary 或表示primary之前再加一个"-"号的组合
     */
    Parser factor = rule().or(rule(NegativeExpr.class).sep("-").ast(primary), primary);

    /**
     * expr: factor {OP factor}
     * expr（表达式）用于表示两个factor之间夹有一个双目运算符的组合
     */
    Parser expr = expr0.expression(BinaryExpr.class, factor, operators);


    Parser statement0 = rule();

    /**
     * block: "{" [ statement ] { (";" | EOL [ statement ] } "}"
     * block（代码块）指的是由{}括起来的 statement（语句） 序列，statement之间需要用分号或换行符(EOL)分隔。
     * 由于stone支持空语句，因此规则中的statement两侧写有中括号[]，可以看到，它的结构大致与expr相似。
     * 它们都由其他的非终结符 （statement 或 factor） 与一些用于分隔的符号组合而成
     */
    Parser block = rule(BlockStmnt.class)
            .sep("{").option(statement0)
            .repeat(rule().sep(";", Token.EOL).option(statement0))
            .sep("}");

    /**
     * simple: expr
     */
    Parser simple = rule(PrimaryExpr.class).ast(expr);

    Parser forPrefix = rule().sep("(").ast(expr).sep(";").ast(expr).sep(";").ast(expr).sep(")");

    /**
     * statement: "if" expr block [ "else" block ]
     *              | "while" expr block
     *              | simple
     * statement可以是if语句、while语句或仅仅是简单的表达式语句（simple）
     */
    Parser statement = statement0.or(
            rule(IfStmnt.class).sep("if").ast(expr).ast(block).option(rule().sep("else").ast(block)),
            rule(WhileStmnt.class).sep("while").ast(expr).ast(block),
            rule(ForStmnt.class).sep("for").ast(forPrefix).ast(block),
            simple);

    /**
     * program: [ statement ] ( ";" | EOL )
     * statement可以省略，因此program还能用来表示空行。
     * 代码块的最后一句能够省略句尾分号和换行符，因此分别设计了statement和program两种类型
     * program既可以处于代码块之外的一条语句，也可以是一行完整的程序
     */
    Parser program = rule().or(statement, rule(NullStmnt.class)).sep(";", Token.EOL);

    public BasicParser() {
        reserved.add(";");
        reserved.add("}");
        reserved.add(Token.EOL);

        operators.add("=", 1, Operators.RIGHT);
        operators.add("==", 2, Operators.LEFT);
        operators.add(">", 2, Operators.LEFT);
        operators.add("<", 2, Operators.LEFT);
        operators.add("+", 3, Operators.LEFT);
        operators.add("-", 3, Operators.LEFT);
        operators.add("*", 4, Operators.LEFT);
        operators.add("/", 4, Operators.LEFT);
        operators.add("%", 4, Operators.LEFT);
    }

    public ASTree parse(Lexer lexer) throws ParseException {
        return program.parse(lexer);
    }
}
