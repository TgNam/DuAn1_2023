/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swing;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
/**
 *
 * @author thiet
 */
public class TableHeaderCustomCellRender extends DefaultTableCellRenderer {

    private JTable table;

    public TableHeaderCustomCellRender(JTable table) {
        this.table = table;
        setOpaque(true);
        setHorizontalAlignment(JLabel.CENTER);
        setFont(new Font("Times New Roman", Font.BOLD, 20));
        setForeground(new Color(250, 250, 250));
        setBackground(new Color(24, 152, 199));
    }

    @Override
    public Component getTableCellRendererComponent(JTable jTable, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(jTable, value, isSelected, hasFocus, row, column);
        return this;
    }
}
