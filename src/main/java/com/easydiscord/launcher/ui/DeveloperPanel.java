package com.easydiscord.launcher.ui;

import com.easydiscord.launcher.config.LauncherConfig;
import com.easydiscord.launcher.ui.components.CustomCheckbox;
import com.easydiscord.launcher.ui.components.CustomButton;

import javax.swing.*;
import java.awt.*;

public class DeveloperPanel extends JPanel {
    private LauncherConfig launcherConfig;

    public DeveloperPanel(LauncherConfig config) {
        this.launcherConfig = config;
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        setBackground(new Color(54, 57, 63));

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(0, 1, 10, 10));
        contentPanel.setBackground(new Color(54, 57, 63));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel devLabel = new JLabel("Режим разработчика");
        devLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        devLabel.setForeground(Color.YELLOW);
        contentPanel.add(devLabel);

        JLabel warningLabel = new JLabel("<html><div style='text-align: center;'>Внимание: Этот режим для продвинутых пользователей.<br>Используйте на свой страх и риск!</div></html>");
        warningLabel.setForeground(Color.ORANGE);
        warningLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(warningLabel);

        CustomCheckbox devModeCheckbox = new CustomCheckbox("Включить Developer Mode лаунчера");
        devModeCheckbox.setSelected(launcherConfig.isDeveloperMode());
        devModeCheckbox.addActionListener(e -> launcherConfig.setDeveloperMode(devModeCheckbox.isSelected()));
        contentPanel.add(devModeCheckbox);

        CustomCheckbox autoUpdateCheckbox = new CustomCheckbox("Автоматическая проверка обновлений");
        autoUpdateCheckbox.setSelected(launcherConfig.isAutoUpdate());
        autoUpdateCheckbox.addActionListener(e -> launcherConfig.setAutoUpdate(autoUpdateCheckbox.isSelected()));
        contentPanel.add(autoUpdateCheckbox);

        // Кнопки для разработчиков
        JPanel devButtonsPanel = new JPanel(new FlowLayout());
        devButtonsPanel.setBackground(new Color(54, 57, 63));

        CustomButton clearCacheButton = new CustomButton("Очистить кэш");
        clearCacheButton.addActionListener(e -> clearCache());
        devButtonsPanel.add(clearCacheButton);

        CustomButton exportConfigButton = new CustomButton("Экспорт конфига");
        exportConfigButton.addActionListener(e -> exportConfig());
        devButtonsPanel.add(exportConfigButton);

        contentPanel.add(devButtonsPanel);

        add(contentPanel, BorderLayout.CENTER);
    }

    private void clearCache() {
        JOptionPane.showMessageDialog(this,
                "Кэш будет очищен при следующем запуске",
                "Очистка кэша",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void exportConfig() {
        JOptionPane.showMessageDialog(this,
                "Конфигурация экспортирована",
                "Экспорт",
                JOptionPane.INFORMATION_MESSAGE);
    }
}