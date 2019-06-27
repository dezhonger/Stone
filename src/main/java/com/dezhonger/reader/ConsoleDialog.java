package com.dezhonger.reader;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by dezhonger on 2019/6/28
 */
public class ConsoleDialog extends Reader {

    private String buffer = null;
    private int pos = 0;

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        if (buffer == null) {
            // FIXME: 2019/6/28 这里可以读取文件
            String in = null;
            if (in == null) {
                return -1;
            } else {
                print(in);
                buffer = in + "\n";
                pos = 0;
            }
        }

        int size = 0;
        int length = buffer.length();
        while (pos < length && size < len) {
            cbuf[off + size++] = buffer.charAt(pos++);
        }

        if (pos == length) {
            buffer = null;
        }

        return size;
    }

    @Override
    public void close() throws IOException {

    }

    protected void print(String s) {
        System.out.println(s);
    }


}
