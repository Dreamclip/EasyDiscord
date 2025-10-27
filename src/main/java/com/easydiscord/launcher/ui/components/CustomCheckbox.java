package com.easydiscord.launcher.ui.components;

import javax.swing.*;
import java.awt.*;

public class CustomCheckbox extends JCheckBox {
    public CustomCheckbox(String text) {
        super(text);
        setFont(new Font("Segoe UI", Font.PLAIN, 14));
        setBackground(new Color(54, 57, 63));
        setForeground(Color.WHITE);
        setFocusPainted(false);
    }
}