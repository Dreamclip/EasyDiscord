package com.easydiscord.launcher.ui.components;

import javax.swing.*;
import java.awt.*;

public class CustomButton extends JButton {
    public CustomButton(String text) {
        super(text);
        setFont(new Font("Segoe UI", Font.BOLD, 14));
        setBackground(new Color(88, 101, 242));
        setForeground(Color.WHITE);
        setFocusPainted(false);
        setBorderPainted(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setBackground(new Color(71, 82, 196));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBackground(new Color(88, 101, 242));
            }
        });
    }
}