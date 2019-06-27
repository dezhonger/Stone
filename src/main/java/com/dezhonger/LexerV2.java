package com.dezhonger;

import com.dezhonger.ui.CodeDialog;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by dezhonger on 2019/6/27
 * 手工设计词法分析器
 */
public class LexerV2 {
    private static final int EMPTY = -1;
    private Reader reader;
    private int lastChar = EMPTY;

    public LexerV2(Reader r) {
        this.reader = r;
    }

    private static boolean isLetter(int c) {
        return 'A' <= c && c <= 'Z' || 'a' <= c && c <= 'z';
    }

    private static boolean isDigit(int c) {
        return '0' <= c && c <= '9';
    }

    private static boolean isSpace(int c) {
        return 0 <= c && c <= ' ';
    }

    /**
     * 通过对话框读取字符
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        LexerV2 l = new LexerV2(new CodeDialog());
        for (String s; (s = l.read()) != null; ) {
            System.out.println("-> " + s);
        }

    }

    private int getChar() throws IOException {
        if (lastChar == EMPTY) {
            return reader.read();
        } else {
            int c = lastChar;
            lastChar = EMPTY;
            return c;
        }
    }

    private void unGetChar(int c) {
        lastChar = c;
    }

    public String read() throws IOException {
        StringBuilder sb = new StringBuilder();
        int c;
        do {
            c = getChar();

        } while (isSpace(c));
        //end of text
        if (c < 0) return null;
        else if (isDigit(c)) {
            do {
                sb.append((char) c);
                c = getChar();
            } while (isDigit(c));
        } else if (isLetter(c)) {
            do {
                sb.append((char) c);
                c = getChar();

            } while (isLetter(c) || isDigit(c));
        } else if (c == '=') {
            c = getChar();
            if (c == '=') {
                return "==";
            } else {
                unGetChar(c);
                return "=";
            }
        } else {
            throw new IOException();
        }

        if (c >= 0) {
            unGetChar(c);
        }
        return sb.toString();
    }

}
