package com.easydiscord.launcher.config;

import java.io.File;

public class LauncherConfig {
    private static final String CONFIG_DIR = System.getProperty("user.home") + File.separator + ".easydiscord";
    private static final String CONFIG_FILE = CONFIG_DIR + File.separator + "launcher_config.json";

    private boolean developerMode = false;
    private boolean autoUpdate = true;
    private String discordPath = "";
    private String theme = "dark";

    // Геттеры и сеттеры
    public boolean isDeveloperMode() { return developerMode; }
    public void setDeveloperMode(boolean developerMode) { this.developerMode = developerMode; }

    public boolean isAutoUpdate() { return autoUpdate; }
    public void setAutoUpdate(boolean autoUpdate) { this.autoUpdate = autoUpdate; }

    public String getDiscordPath() { return discordPath; }
    public void setDiscordPath(String discordPath) { this.discordPath = discordPath; }

    public String getTheme() { return theme; }
    public void setTheme(String theme) { this.theme = theme; }

    public String getConfigDir() { return CONFIG_DIR; }
    public String getConfigFile() { return CONFIG_FILE; }
}