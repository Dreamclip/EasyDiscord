// DeveloperPanel.java
package com.easydiscord.launcher.ui.components;

import com.easydiscord.launcher.config.LauncherConfig;
import javax.swing.*;
import java.awt.*;

public class DeveloperPanel extends JPanel {
    private LauncherConfig config;
    private JTextArea jsonEditor;
    
    public DeveloperPanel(LauncherConfig config) {
        this.config = config;
        initializeUI();
    }
    
    private void initializeUI() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel titleLabel = new JLabel("Режим разработчика - редактирование конфигурации");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
        jsonEditor = new JTextArea(20, 50);
        jsonEditor.setFont(new Font("Monospaced", Font.PLAIN, 12));
        jsonEditor.setText(configToJson());
        
        JScrollPane scrollPane = new JScrollPane(jsonEditor);
        
        JButton applyButton = new CustomButton("Применить изменения");
        applyButton.addActionListener(e -> applyJsonChanges());
        
        JButton reloadButton = new CustomButton("Перезагрузить");
        reloadButton.addActionListener(e -> jsonEditor.setText(configToJson()));
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(applyButton);
        buttonPanel.add(reloadButton);
        
        add(titleLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private String configToJson() {
        com.google.gson.Gson gson = new com.google.gson.GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(config);
    }
    
    private void applyJsonChanges() {
        try {
            com.google.gson.Gson gson = new com.google.gson.Gson();
            LauncherConfig newConfig = gson.fromJson(jsonEditor.getText(), LauncherConfig.class);
            
            // Обновляем конфигурацию
            config.setDeveloperMode(newConfig.isDeveloperMode());
            config.setAutoUpdate(newConfig.isAutoUpdate());
            config.setTheme(newConfig.getTheme());
            config.setDiscordPath(newConfig.getDiscordPath());
            config.setDiscordConfig(newConfig.getDiscordConfig());
            
            config.save();
            JOptionPane.showMessageDialog(this, "Конфигурация обновлена!", "Успех", 
                                        JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Ошибка в JSON: " + e.getMessage(), 
                "Ошибка", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
}