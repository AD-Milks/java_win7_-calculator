/**
 * Project Name:Calculator
 * File Name:Listeners.java
 * Package Name:com.Calculator
 * Date:下午04:14:45
 * Copyright (c) 2021, I All Rights Reserved.
 *
 */

package com.Calculator;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Description: <br/>
 * Date: 下午04:14:45 <br/>
 * 
 * @author AD_MiLks
 * @version
 * @see
 */
public class Listeners implements ActionListener {

    // 利用Toolkit工具包类取得系统工具包，在系统工具包中取得系统剪贴板
    Clipboard TEMP = Toolkit.getDefaultToolkit().getSystemClipboard();

    static JTextField txt;

    private static JMenuItem Jm1;

    String[] NUM = { "7", "8", "9", "/", "sqrt", "4", "5", "6", "*", "%", "1", "2", "3", "-", "1/x", "0", "+/-", ".",
            "+", "=" };

    String[] DEL = { "Backspace", "CE", "C" };

    // 当前运算的运算符

    static String operator = "=";

    // 计算的中间结果
    double renturnnum = 0;

    // 按的是否是整个表达式的第一个数字,或者是运算符后的第一个数字
    static boolean firstNum = true;

    // 输入是否合法
    boolean inputCorrvt = true;

    public Listeners(JTextField txt) {

        Listeners.txt = txt;
    }

    public Listeners(JButton[] num) {
    }

    public Listeners(JMenuItem Jm1) {

        Listeners.Jm1 = Jm1;

    }

    public void actionPerformed(ActionEvent e) {
        // 获取事件源
        String label = e.getActionCommand();

        // 判断不同按键，对应不同事件
        if (label.equals(DEL[0])) {
            // 删除键
            BackspaceEvent();

        } else if (label.equals(DEL[1])) {
            // CE键
            txt.setText("0");
            // System.out.println("CE");

        } else if (label.equals(DEL[2])) {
            // C键
            CEvent();

        } else if ("0123456789.".contains(label)) {
            // 数字键
            NumEvent(label);

        } else if (label.equals("关于")) {
            // 菜单栏关于键
            about();

        } else if (label.equals("复制")) {
            // 菜单栏复制键
            copy();

        } else if (label.equals("粘贴")) {
            // 菜单栏复制键
            paste();

        } else {
            // 运算符
            SymbolEvent(label);
        }

    }

    /**
     *功能描述 按钮BackspaceEvent的处理
     * 
     * @author AD_MiLks
     * @date 2021/6/9
     */
    private void BackspaceEvent() {
        // 获取文本框内容
        String text = txt.getText();
        // 获取文本框字符个数
        int i = text.length();
        if (i > 0) {
            // 将文本最后一个字符去掉
            text = text.substring(0, i - 1);
            if (text.length() == 0) {
                // 如果文本没有了内容，则初始化计算器的各种值
                txt.setText("0");
                firstNum = true;
                operator = "=";
            } else {
                // 显示新的文本
                txt.setText(text);
            }
        }
        // System.out.println("Back");
    }

    /**
     *功能描述 按钮C的处理
     * 
     * @author AD_MiLks
     * @date 2021/6/11
     */
    static void CEvent() {
        // 初始化计算器的各种值
        txt.setText("0");
        firstNum = true;
        operator = "=";
        // System.out.println("C");
    }

    /**
     *功能描述 数字的处理
     * 
     * @author AD_MiLks
     * @date 2021/6/11
     */
    private void NumEvent(String num) {
        if (firstNum) {
            txt.setText(String.valueOf(num));

        } else if ((num.equals(".")) && (!txt.getText().contains("."))) {
            // 输入的是小数点，并且之前没有小数点，则将小数点附在结果文本框的后面
            txt.setText(txt.getText() + ".");

        } else if (!num.equals(".")) {
            // 如果输入的不是小数点，则将数字附在结果文本框的后面
            txt.setText(txt.getText() + num);

        }
        // System.out.print(num);
        firstNum = false;

    }

    /**
     *功能描述 符号的处理
     * 
     * @author AD_MiLks
     * @date 2021/6/12
     */
    private void SymbolEvent(String num) {

        if (operator.equals("/")) {
            // 除数为0
            // System.out.print(getnumtxt());
            if (renturnnum == 0) {
                inputCorrvt = false;
                // System.out.print("除数为0，错误");
                txt.setText("除数为0，错误");
            } else if (getnumtxt() == 0) {
                // 任何数除0都为0
                renturnnum = 0;
                // System.out.print("任何数除0都为0");
            } else {

                renturnnum /= getnumtxt();
                // System.out.println("/");
            }
        } else if (operator.equals("*")) {

            renturnnum *= getnumtxt();
            // System.out.println("*");
        } else if (operator.equals("+")) {

            renturnnum += getnumtxt();
            // System.out.print("+");
        } else if (operator.equals("-")) {

            renturnnum -= getnumtxt();
            // System.out.print("-");
        } else if (operator.equals("sqrt")) {
            // 平方根运算
            renturnnum = Math.sqrt(renturnnum);
            // System.out.print("sqrt");
        } else if (operator.equals("%")) {
            // 百分号运算，除以100
            renturnnum = renturnnum / 100;
            // System.out.print("%");
        } else if (operator.equals("+/-")) {
            // 正数负数运算
            renturnnum = renturnnum * (-1);
            // System.out.print("(-" + renturnnum + ")");
        } else if (operator.equals("1/x")) {
            // 倒数运算
            if (renturnnum == 0.0) {
                // 操作不合法
                inputCorrvt = false;
                txt.setText("零没有倒数");
            } else {
                renturnnum = 1 / renturnnum;
                // System.out.print("1/" + renturnnum);
            }
        } else if (operator.equals("=")) {
            // 赋值运算
            renturnnum = getnumtxt();
            // System.out.println("==");
        }
        /*
         * 如果结果为小数，直接输出； 判断结果为整数，输出整数。
         */
        long t1 = (long) renturnnum;
        double t2 = renturnnum - t1;
        if (inputCorrvt) {
            if (t2 == 0) {
                String JieGuot1 = String.valueOf(t1);

                txt.setText(JieGuot1);
                // System.out.println(JieGuot1);
            } else {
                String JieGuot2 = String.valueOf(renturnnum);
                txt.setText(JieGuot2);

                // System.out.println(JieGuot2);
            }

        }

        operator = num;
        firstNum = true;
        inputCorrvt = true;

    }

    /**
     *功能描述 获取文本框的内容，转换成double，
     * 
     * @author AD_MiLks
     * @date 2021/6/13
     */
    private double getnumtxt() {
        double result = 0;
        try {
            /*
             * 先把txt文本框中的数字提取出来，转换成 double ， 根据百度 doubleValue()需要套用两次使用
             */
            double resultn = 0;
            // 把txt转为String
            String resultsString = txt.getText();
            // 把String转为double，开始第一次套用
            resultn = Double.parseDouble(resultsString);
            // 开始第二次套用
            Double b = new Double(resultn);
            // 赋值
            result = b.doubleValue();
            // 测试result
            // System.out.println("result为：" + result);
        } catch (Exception e) {

        }

        return result;
    }

    /**
     *功能描述 菜单栏关于的处理
     * 
     * @author AD_MiLks
     * @date 2021/6/16
     */
    private void about() {
        // 新建对话框
        JDialog dialog2 = new JDialog(MIan.BigBox);
        // 对话框名字
        dialog2.setTitle("关于");
        // 对话框大小
        dialog2.setSize(470, 175);
        // 对话框位置
        dialog2.setLocation(350, 450);
        // 对话框显示
        dialog2.setVisible(true);
        // 对话框大小不允许修改
        dialog2.setResizable(false);
        // 窗口的关闭按钮时程序执行的操作
        dialog2.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        // 对话框内新建文本框，文本框内填充内容
        JTextArea text1 = new JTextArea("\n" + "    本软件可实现基本计算器的功能，实现了加、减、乘、除、" + "\n" + "开平方等简单运算的功能，"
                + "与标准计算器相比较大大的降低了数" + "\n" + "字计算的难度，并且提高了计算的准确度和精确度。" + "\n" + "\n" + "\t作者：AD_MiLks");
        // 为文本框的文字修改字体
        Font Fonts = new Font("仿宋", Font.BOLD, 15);
        // 将字体设置给文本
        text1.setFont(Fonts);
        // 文本框不允许修改
        text1.setEditable(false);
        // 文本框添加到对话框
        dialog2.add(text1);

    }

    /**
     *功能描述 菜单栏复制的处理
     * 
     * @author AD_MiLks
     * @date 2021/6/17
     */
    public void copy() {
        /*
         * setContents(可转让的内容、所有者);
         * 
         * 讲剪切板的内容设置到指定的可转让的对象,并将指定的剪切板所有者作为新内容的所有者注册
         */
        StringSelection temps = new StringSelection(txt.getText());
        TEMP.setContents(temps, null);

    }

    /*
     * 关于剪贴板复制与粘贴详见https://blog.csdn.net/paul50060049/article/details/51838225
     */
    /**
     *功能描述 菜单栏粘贴的处理
     * 
     * @author AD_MiLks
     * @date 2021/6/18
     */
    public void paste() {
        // 首选要判断剪贴板中是否包含可以剪贴的内容
        if (TEMP.isDataFlavorAvailable(DataFlavor.stringFlavor)) {

            String str = null;

            try {

                str = (String) TEMP.getData(DataFlavor.stringFlavor);
                if (checkStrIsNum(str) == true) {

                    txt.setText(str);
                } else {
                    txt.setText("请粘贴数字");
                }

            } catch (UnsupportedFlavorException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    private static Pattern NUMBER_PATTERN = Pattern.compile("-?[0-9]+(\\.[0-9]+)?");

    /**
     * 利用正则表达式来判断字符串是否为数字
     */
    public static boolean checkStrIsNum(String str) {
        String bigStr;
        try {
            /** 先将str转成BigDecimal，然后在转成String */
            bigStr = new BigDecimal(str).toString();
        } catch (Exception e) {
            /** 如果转换数字失败，说明该str并非全部为数字 */
            return false;
        }
        Matcher isNum = NUMBER_PATTERN.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

}
