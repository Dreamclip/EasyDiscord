package com.easydiscord.launcher.ui;

import com.easydiscord.launcher.config.LauncherConfig;
import com.easydiscord.launcher.config.DiscordConfig;
import com.easydiscord.launcher.discord.DiscordManager;
import com.easydiscord.launcher.updater.UpdateChecker;
import com.easydiscord.launcher.utils.FileUtils;
import com.easydiscord.launcher.utils.JSONUtils;
import com.easydiscord.launcher.ui.components.CustomButton;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private LauncherConfig launcherConfig;
    private DiscordConfig discordConfig;
    private JTabbedPane tabbedPane;
    private SettingsPanel settingsPanel;
    private DeveloperPanel developerPanel;

    public MainFrame() {
        initializeConfig();
        initializeUI();
        loadConfig();
    }

    private void initializeConfig() {
        // Создаем директорию для конфигов
        FileUtils.createDirectory(launcherConfig.getConfigDir());

        // Загружаем конфигурации
        launcherConfig = JSONUtils.loadFromFile(launcherConfig.getConfigFile(), LauncherConfig.class);
        discordConfig = JSONUtils.loadFromFile(launcherConfig.getConfigDir() + "/discord_config.json", DiscordConfig.class);

        // Если путь к Discord не установлен, пытаемся найти автоматически
        if (launcherConfig.getDiscordPath().isEmpty()) {
            launcherConfig.setDiscordPath(FileUtils.findDiscordPath());
        }
    }

    private void initializeUI() {
        setTitle("EasyDiscord Launcher");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        // Устанавливаем темную тему
        try {
            UIManager.setLookAndFeel(UIManager.getLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Создаем основной интерфейс
        createMainPanel();
    }

    private void createMainPanel() {
        setLayout(new BorderLayout());

        // Заголовок
        JLabel titleLabel = new JLabel("EasyDiscord", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(88, 101, 242));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titleLabel, BorderLayout.NORTH);

        // Панель с вкладками
        tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(new Color(47, 49, 54));
        tabbedPane.setForeground(Color.WHITE);

        settingsPanel = new SettingsPanel(discordConfig);
        tabbedPane.addTab("Основные настройки", settingsPanel);

        if (launcherConfig.isDeveloperMode()) {
            developerPanel = new DeveloperPanel(launcherConfig);
            tabbedPane.addTab("Режим разработчика", developerPanel);
        }

        add(tabbedPane, BorderLayout.CENTER);

        // Панель с кнопками
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(new Color(47, 49, 54));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

        CustomButton launchButton = new CustomButton("Запустить Discord");
        launchButton.addActionListener(e -> launchDiscord());

        CustomButton saveButton = new CustomButton("Сохранить настройки");
        saveButton.addActionListener(e -> saveConfig());

        buttonPanel.add(launchButton);
        buttonPanel.add(saveButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void launchDiscord() {
        saveConfig();

        // Проверяем обновления
        UpdateChecker.checkForUpdates(launcherConfig.isAutoUpdate());

        // Запускаем Discord
        boolean success = DiscordManager.launchDiscord(launcherConfig.getDiscordPath(), discordConfig);

        if (success) {
            JOptionPane.showMessageDialog(this,
                    "Discord запущен с выбранными настройками!",
                    "Успех",
                    JOptionPane.INFORMATION_MESSAGE);

            // Закрываем лаунчер после успешного запуска
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Не удалось запустить Discord. Проверьте путь к приложению.",
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadConfig() {
        // Конфиг уже загружен в initializeConfig()
        System.out.println("Конфигурация загружена");
    }

    private void saveConfig() {
        JSONUtils.saveToFile(launcherConfig.getConfigFile(), launcherConfig);
        JSONUtils.saveToFile(launcherConfig.getConfigDir() + "/discord_config.json", discordConfig);

        JOptionPane.showMessageDialog(this,
                "Настройки сохранены!",
                "Сохранение",
                JOptionPane.INFORMATION_MESSAGE);
    }
}