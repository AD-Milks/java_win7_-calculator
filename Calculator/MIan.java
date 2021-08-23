/**
 * Project Name:Calculator
 * File Name:MIan.java
 * Package Name:com.Calculator
 * Date:下午04:13:53
 * Copyright (c) 2021, I All Rights Reserved.
 *
 */

package com.Calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

/**
 * Description: Main method Date: 下午04:13:53 <br/>
 * 
 * @author AD_MiLks
 * @version
 * @see
 */
public class MIan {
    static JFrame BigBox = new JFrame();

    private static Listeners jm1;

    private static Listeners jm2;

    private static Listeners jm3;

    public static void intiBigBOX() {

        // 面板大小
        BigBox.setSize(600, 450);
        // 面板位置
        BigBox.setLocation(300, 300);
        // 面板可见
        BigBox.setVisible(true);
        // 面板颜色
        BigBox.setBackground(Color.white);
        // 面板名称
        BigBox.setTitle("计算器");
        // 面板大小不允许修改
        BigBox.setResizable(false);

        BorderLayout border_Layout = new BorderLayout();
        BigBox.setLayout(border_Layout);

        JTextField panel_top = CreatePanel.top_panel(BigBox);
        BigBox.add(panel_top, BorderLayout.PAGE_START);

        JPanel panel_center = CreatePanel.center_panel(BigBox);
        BigBox.add(panel_center, BorderLayout.CENTER);

        JPanel panel_west = CreatePanel.west_panel(BigBox);
        BigBox.add(panel_west, BorderLayout.PAGE_END);

        JMenuBar menu = new JMenuBar();
        JMenu jMenu1 = new JMenu("编辑");
        JMenu jMenu2 = new JMenu("其他");

        menu.add(jMenu1);
        menu.add(jMenu2);

        JMenuItem jMenuI1 = new JMenuItem("关于", KeyEvent.VK_H);
        JMenuItem jMenuI2 = new JMenuItem("复制", KeyEvent.VK_C);
        JMenuItem jMenuI3 = new JMenuItem("粘贴", KeyEvent.VK_V);

        jMenu1.add(jMenuI2);
        jMenu1.add(jMenuI3);
        jMenu2.add(jMenuI1);

        jm1 = new Listeners(jMenuI1);
        jMenuI1.addActionListener(jm1);
        jMenuI1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
        jm2 = new Listeners(jMenuI2);
        jMenuI2.addActionListener(jm2);
        jMenuI2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        jm3 = new Listeners(jMenuI3);
        jMenuI3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        jMenuI3.addActionListener(jm3);
        Listeners.CEvent();
        BigBox.setJMenuBar(menu);
        BigBox.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Description: Program method body
     * 
     * @author AD_MiLks
     * @param args
     */
    public static void main(String[] args) {

        // Auto-generated method stub
        new MIan();

        MIan.intiBigBOX();

    }

}
