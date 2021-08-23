/**
 * Project Name:Calculator
 * File Name:CreatePanel.java
 * Package Name:com.Calculator
 * Date:下午04:14:29
 * Copyright (c) 2021, I All Rights Reserved.
 *
 */

package com.Calculator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Description: <br/>
 * Date: 下午04:14:29 <br/>
 * 
 * @author AD_MiLks
 * @version
 * @see
 */
public class CreatePanel {

    private static Listeners tt;

    private static Listeners nn;

    private static Listeners dd;

    public static JTextField top_panel(JFrame BigBOx) {
        JPanel top = new JPanel();
        Font Fonts3 = new Font("楷书", Font.BOLD, 20);
        JTextField txt = new JTextField();
        txt.setFont(Fonts3);
        txt.setHorizontalAlignment(JTextField.RIGHT);
        txt.setEditable(false);
        txt.setBackground(Color.WHITE);
        txt.setPreferredSize(new Dimension(550, 50));
        top.add(txt);
        tt = new Listeners(txt);
        txt.addActionListener(tt);
        return txt;

    }

    public static JPanel center_panel(JFrame BigBOx) {
        String[] NUM = { "7", "8", "9", "/", "sqrt", "4", "5", "6", "*", "%", "1", "2", "3", "-", "1/x", "0", "+/-",
                ".", "+", "=" };
        JButton num[] = new JButton[NUM.length];
        JPanel center = new JPanel();
        Font Fonts1 = new Font("楷书", Font.BOLD, 18);
        center.setLayout(new GridLayout(4, 5, 4, 4));
        for (int i = 0; i < NUM.length; i++) {
            num[i] = new JButton(NUM[i]);
            center.add(num[i]);
            num[i].setForeground(Color.blue);
            num[i].setFont(Fonts1);
        }
        num[3].setForeground(Color.red);
        num[8].setForeground(Color.red);
        num[13].setForeground(Color.red);
        num[18].setForeground(Color.red);
        num[19].setForeground(Color.red);
        num[4].setForeground(Color.black);
        num[9].setForeground(Color.black);
        num[14].setForeground(Color.black);
        num[16].setForeground(Color.black);
        num[17].setForeground(Color.black);
        nn = new Listeners(num);
        for (int c = 0; c < num.length; c++) {

            num[c].addActionListener(nn);
        }
        return center;
    }

    public static JPanel west_panel(JFrame BigBOx) {
        String[] DEL = { "Backspace", "CE", "C" };
        JButton[] del = new JButton[DEL.length];
        JPanel west = new JPanel();
        Font Fonts2 = new Font("行书", Font.BOLD, 19);
        west.setLayout(new GridLayout(1, 3, 3, 3));
        for (int K = 0; K < DEL.length; K++) {
            del[K] = new JButton(DEL[K]);
            west.add(del[K]);
            del[K].setForeground(Color.red);
            del[K].setPreferredSize(new Dimension(50, 50));
            del[K].setFont(Fonts2);
        }
        dd = new Listeners(del);
        for (int w = 0; w < del.length; w++) {

            del[w].addActionListener(dd);
        }

        return west;
    }

}
