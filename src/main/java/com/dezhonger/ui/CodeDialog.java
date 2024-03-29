package com.dezhonger.ui;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Created by dezhonger on 2019/6/25
 */
public class CodeDialog extends Reader  {
    private String buffer = null;
    private int pos = 0;

    public static Reader file() throws FileNotFoundException {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            return new BufferedReader(new FileReader(chooser.getSelectedFile()));
        } else {
            throw new FileNotFoundException("no file specified");
        }
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        if (buffer == null) {
            String in = showDialog();
//            System.out.println("start");
//            System.out.println("in: " + in);
//            System.out.println("end");
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

    protected String showDialog() {
        JTextArea area = new JTextArea(20, 40);
        JScrollPane pane = new JScrollPane(area);
        int result = JOptionPane.showOptionDialog(null, pane, "Input",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null, null, null);

        if (result == JOptionPane.OK_OPTION) {
            return area.getText().trim();
        } else {
            return null;
        }
    }
}
