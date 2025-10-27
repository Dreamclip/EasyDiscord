package com.easydiscord.launcher;

import com.easydiscord.launcher.ui.MainFrame;
import javax.swing.*;

public class EasyDiscordLauncher {
    public static void main(String[] args) {
        // Устанавливаем настройки для улучшения отображения
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");

        // Запускаем UI в Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            try {
                new MainFrame().setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "Ошибка запуска лаунчера: " + e.getMessage(),
                        "Ошибка",
                        JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        });
    }
}