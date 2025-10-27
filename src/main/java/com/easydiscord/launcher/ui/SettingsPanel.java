package com.easydiscord.launcher.ui;

import com.easydiscord.launcher.config.DiscordConfig;
import com.easydiscord.launcher.ui.components.CustomCheckbox;
import com.easydiscord.launcher.ui.components.CustomButton;

import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends JPanel {
    private DiscordConfig discordConfig;
    private JTextField discordPathField;

    public SettingsPanel(DiscordConfig config) {
        this.discordConfig = config;
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        setBackground(new Color(54, 57, 63));

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(0, 1, 10, 10));
        contentPanel.setBackground(new Color(54, 57, 63));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Настройки Discord
        JLabel discordLabel = new JLabel("Настройки Discord");
        discordLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        discordLabel.setForeground(Color.WHITE);
        contentPanel.add(discordLabel);

        CustomCheckbox devModeCheckbox = new CustomCheckbox("Включить Developer Mode в Discord");
        devModeCheckbox.setSelected(discordConfig.isDeveloperMode());
        devModeCheckbox.addActionListener(e -> discordConfig.setDeveloperMode(devModeCheckbox.isSelected()));
        contentPanel.add(devModeCheckbox);

        CustomCheckbox removeGiftCheckbox = new CustomCheckbox("Убрать кнопку подарков");
        removeGiftCheckbox.setSelected(discordConfig.isRemoveGiftButton());
        removeGiftCheckbox.addActionListener(e -> discordConfig.setRemoveGiftButton(removeGiftCheckbox.isSelected()));
        contentPanel.add(removeGiftCheckbox);

        CustomCheckbox removeNitroCheckbox = new CustomCheckbox("Убрать кнопку Nitro");
        removeNitroCheckbox.setSelected(discordConfig.isRemoveNitroButton());
        removeNitroCheckbox.addActionListener(e -> discordConfig.setRemoveNitroButton(removeNitroCheckbox.isSelected()));
        contentPanel.add(removeNitroCheckbox);

        CustomCheckbox compactModeCheckbox = new CustomCheckbox("Компактный режим");
        compactModeCheckbox.setSelected(discordConfig.isCompactMode());
        compactModeCheckbox.addActionListener(e -> discordConfig.setCompactMode(compactModeCheckbox.isSelected()));
        contentPanel.add(compactModeCheckbox);

        CustomCheckbox transparentWindowCheckbox = new CustomCheckbox("Прозрачное окно");
        transparentWindowCheckbox.setSelected(discordConfig.isTransparentWindow());
        transparentWindowCheckbox.addActionListener(e -> discordConfig.setTransparentWindow(transparentWindowCheckbox.isSelected()));
        contentPanel.add(transparentWindowCheckbox);

        // Путь к Discord
        JPanel pathPanel = new JPanel(new BorderLayout(10, 10));
        pathPanel.setBackground(new Color(54, 57, 63));

        JLabel pathLabel = new JLabel("Путь к Discord:");
        pathLabel.setForeground(Color.WHITE);
        pathPanel.add(pathLabel, BorderLayout.WEST);

        discordPathField = new JTextField(discordConfig.getCustomTheme());
        discordPathField.setBackground(new Color(64, 68, 75));
        discordPathField.setForeground(Color.WHITE);
        discordPathField.setCaretColor(Color.WHITE);
        pathPanel.add(discordPathField, BorderLayout.CENTER);

        CustomButton browseButton = new CustomButton("Обзор");
        browseButton.addActionListener(e -> browseForDiscord());
        pathPanel.add(browseButton, BorderLayout.EAST);

        contentPanel.add(pathPanel);

        add(contentPanel, BorderLayout.CENTER);
    }

    private void browseForDiscord() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            discordPathField.setText(fileChooser.getSelectedFile().getAbsolutePath());
        }
    }

    public String getDiscordPath() {
        return discordPathField.getText();
    }
}