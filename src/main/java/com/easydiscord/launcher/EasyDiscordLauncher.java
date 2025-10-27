// EasyDiscordLauncher.java
package com.easydiscord.launcher;

import com.easydiscord.launcher.config.LauncherConfig;
import com.easydiscord.launcher.ui.MainFrame;
import javax.swing.*;

public class EasyDiscordLauncher {
    public static void main(String[] args) {
        // Устанавливаем красивый Look and Feel
        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatDarkLaf());
        } catch (Exception e) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
        SwingUtilities.invokeLater(() -> {
            // Загружаем конфигурацию
            LauncherConfig config = LauncherConfig.load();
            
            // Создаем и показываем главное окно
            MainFrame mainFrame = new MainFrame(config);
            mainFrame.setVisible(true);
        });
    }
}