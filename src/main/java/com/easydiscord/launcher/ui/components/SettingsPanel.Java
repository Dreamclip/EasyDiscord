// SettingsPanel.java
package com.easydiscord.launcher.ui.components;

import com.easydiscord.launcher.config.LauncherConfig;
import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends JPanel {
    private LauncherConfig config;
    
    private JCheckBox developerModeCheck;
    private JCheckBox autoUpdateCheck;
    private JCheckBox discordDevModeCheck;
    private JCheckBox removeClutterCheck;
    private JCheckBox compactModeCheck;
    private JCheckBox experimentalFeaturesCheck;
    private JCheckBox customCssCheck;
    private JTextArea customCssArea;
    
    public SettingsPanel(LauncherConfig config) {
        this.config = config;
        initializeUI();
        loadSettings();
    }
    
    private void initializeUI() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        
        // Секция лаунчера
        JPanel launcherSection = createSectionPanel("Настройки лаунчера");
        developerModeCheck = new JCheckBox("Режим разработчика лаунчера");
        autoUpdateCheck = new JCheckBox("Автоматически проверять обновления");
        
        launcherSection.add(developerModeCheck);
        launcherSection.add(autoUpdateCheck);
        
        // Секция Discord
        JPanel discordSection = createSectionPanel("Настройки Discord");
        discordDevModeCheck = new JCheckBox("Developer Mode в Discord");
        removeClutterCheck = new JCheckBox("Убрать лишние элементы");
        compactModeCheck = new JCheckBox("Компактный режим");
        experimentalFeaturesCheck = new JCheckBox("Экспериментальные функции");
        
        discordSection.add(discordDevModeCheck);
        discordSection.add(removeClutterCheck);
        discordSection.add(compactModeCheck);
        discordSection.add(experimentalFeaturesCheck);
        
        // Секция кастомного CSS
        JPanel cssSection = createSectionPanel("Кастомный CSS");
        customCssCheck = new JCheckBox("Включить кастомный CSS");
        customCssArea = new JTextArea(8, 40);
        customCssArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane cssScroll = new JScrollPane(customCssArea);
        
        cssSection.add(customCssCheck);
        cssSection.add(new JLabel("CSS код:"));
        cssSection.add(cssScroll);
        
        // Добавляем все секции
        mainPanel.add(launcherSection);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(discordSection);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(cssSection);
        
        // Кнопка сохранения
        JButton saveButton = new CustomButton("Сохранить настройки");
        saveButton.addActionListener(e -> saveSettings());
        
        add(mainPanel, BorderLayout.CENTER);
        add(saveButton, BorderLayout.SOUTH);
    }
    
    private JPanel createSectionPanel(String title) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(88, 101, 242), 2), 
            title
        ));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        return panel;
    }
    
    private void loadSettings() {
        developerModeCheck.setSelected(config.isDeveloperMode());
        autoUpdateCheck.setSelected(config.isAutoUpdate());
        
        discordDevModeCheck.setSelected(config.getDiscordConfig().isDeveloperMode());
        removeClutterCheck.setSelected(config.getDiscordConfig().isRemoveClutter());
        compactModeCheck.setSelected(config.getDiscordConfig().isCompactMode());
        experimentalFeaturesCheck.setSelected(config.getDiscordConfig().isExperimentalFeatures());
        
        customCssCheck.setSelected(config.getDiscordConfig().isCustomCssEnabled());
        customCssArea.setText(config.getDiscordConfig().getCustomCss());
    }
    
    private void saveSettings() {
        config.setDeveloperMode(developerModeCheck.isSelected());
        config.setAutoUpdate(autoUpdateCheck.isSelected());
        
        config.getDiscordConfig().setDeveloperMode(discordDevModeCheck.isSelected());
        config.getDiscordConfig().setRemoveClutter(removeClutterCheck.isSelected());
        config.getDiscordConfig().setCompactMode(compactModeCheck.isSelected());
        config.getDiscordConfig().setExperimentalFeatures(experimentalFeaturesCheck.isSelected());
        
        config.getDiscordConfig().setCustomCssEnabled(customCssCheck.isSelected());
        config.getDiscordConfig().setCustomCss(customCssArea.getText());
        
        config.save();
        JOptionPane.showMessageDialog(this, "Настройки сохранены!", "Успех", 
                                    JOptionPane.INFORMATION_MESSAGE);
    }
}