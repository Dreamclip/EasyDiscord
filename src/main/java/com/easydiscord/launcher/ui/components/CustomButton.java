// CustomButton.java
package com.easydiscord.launcher.ui.components;

import javax.swing.*;
import java.awt.*;

public class CustomButton extends JButton {
    public CustomButton(String text) {
        super(text);
        setFocusPainted(false);
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        setBackground(new Color(88, 101, 242));
        setForeground(Color.WHITE);
        setFont(new Font("Arial", Font.BOLD, 14));
        
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