// MainFrame.java
package com.easydiscord.launcher.ui;

import com.easydiscord.launcher.config.LauncherConfig;
import com.easydiscord.launcher.ui.components.SettingsPanel;
import com.easydiscord.launcher.ui.components.DeveloperPanel;
import com.easydiscord.launcher.utils.DiscordStarter;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private LauncherConfig config;
    private JTabbedPane tabbedPane;
    private SettingsPanel settingsPanel;
    private DeveloperPanel developerPanel;
    
    public MainFrame(LauncherConfig config) {
        this.config = config;
        initializeUI();
        setupEvents();
    }
    
    private void initializeUI() {
        setTitle("EasyDiscord Launcher");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setIconImage(createAppIcon());
        
        // Создаем вкладки
        tabbedPane = new JTabbedPane();
        
        // Панель основных настроек
        settingsPanel = new SettingsPanel(config);
        tabbedPane.addTab("Настройки", settingsPanel);
        
        // Панель для разработчиков (только если включен режим разработчика)
        if (config.isDeveloperMode()) {
            developerPanel = new DeveloperPanel(config);
            tabbedPane.addTab("Developer", developerPanel);
        }
        
        // Панель запуска
        JPanel launchPanel = createLaunchPanel();
        tabbedPane.addTab("Запуск", launchPanel);
        
        add(tabbedPane, BorderLayout.CENTER);
    }
    
    private JPanel createLaunchPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Информация о конфигурации
        JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setBackground(new Color(30, 30, 30));
        infoArea.setForeground(Color.WHITE);
        infoArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        infoArea.setText(getConfigSummary());
        
        JScrollPane scrollPane = new JScrollPane(infoArea);
        scrollPane.setPreferredSize(new Dimension(500, 300));
        
        // Кнопка запуска
        JButton launchButton = new JButton("Запустить Discord");
        launchButton.setFont(new Font("Arial", Font.BOLD, 16));
        launchButton.setBackground(new Color(88, 101, 242));
        launchButton.setForeground(Color.WHITE);
        launchButton.setFocusPainted(false);
        launchButton.addActionListener(e -> launchDiscord());
        
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(launchButton, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private void setupEvents() {
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                config.save();
            }
        });
    }
    
    private void launchDiscord() {
        try {
            boolean success = DiscordStarter.launchDiscord(config);
            if (success) {
                JOptionPane.showMessageDialog(this, 
                    "Discord запущен с выбранными настройками!", 
                    "Успех", 
                    JOptionPane.INFORMATION_MESSAGE);
                config.save();
            } else {
                JOptionPane.showMessageDialog(this,
                    "Не удалось запустить Discord. Проверьте путь к приложению.",
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Ошибка при запуске: " + e.getMessage(),
                "Ошибка",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private String getConfigSummary() {
        return String.format(
            "=== Конфигурация EasyDiscord ===\n\n" +
            "Режим разработчика: %s\n" +
            "Discord Developer Mode: %s\n" +
            "Убрать мусор: %s\n" +
            "Компактный режим: %s\n" +
            "Кастомный CSS: %s\n" +
            "Экспериментальные функции: %s\n" +
            "Путь к Discord: %s\n\n" +
            "=== Параметры запуска ===\n%s",
            config.isDeveloperMode(),
            config.getDiscordConfig().isDeveloperMode(),
            config.getDiscordConfig().isRemoveClutter(),
            config.getDiscordConfig().isCompactMode(),
            config.getDiscordConfig().isCustomCssEnabled(),
            config.getDiscordConfig().isExperimentalFeatures(),
            config.getDiscordPath(),
            DiscordStarter.getLaunchCommand(config)
        );
    }
    
    private Image createAppIcon() {
        // Создаем простую иконку
        BufferedImage icon = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = icon.createGraphics();
        
        // Градиентный фон
        GradientPaint gradient = new GradientPaint(0, 0, new Color(88, 101, 242), 
                                                 32, 32, new Color(255, 115, 250));
        g2d.setPaint(gradient);
        g2d.fillRoundRect(0, 0, 32, 32, 8, 8);
        
        // Буква "E"
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.drawString("E", 10, 22);
        
        g2d.dispose();
        return icon;
    }
}