// LauncherConfig.java
package com.easydiscord.launcher.config;

import com.easydiscord.launcher.utils.FileUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;

public class LauncherConfig {
    private boolean developerMode;
    private boolean autoUpdate;
    private String theme;
    private String discordPath;
    private DiscordConfig discordConfig;
    
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final File CONFIG_FILE = new File("config/launcher.json");
    
    public LauncherConfig() {
        this.developerMode = false;
        this.autoUpdate = true;
        this.theme = "dark";
        this.discordPath = findDiscordPath();
        this.discordConfig = new DiscordConfig();
    }
    
    private String findDiscordPath() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            return System.getenv("LOCALAPPDATA") + "/Discord/app-*/Discord.exe";
        } else if (os.contains("mac")) {
            return "/Applications/Discord.app/Contents/MacOS/Discord";
        } else {
            return "/usr/bin/discord";
        }
    }
    
    public static LauncherConfig load() {
        try {
            if (CONFIG_FILE.exists()) {
                String json = FileUtils.readFile(CONFIG_FILE);
                return gson.fromJson(json, LauncherConfig.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new LauncherConfig();
    }
    
    public void save() {
        try {
            CONFIG_FILE.getParentFile().mkdirs();
            String json = gson.toJson(this);
            FileUtils.writeFile(CONFIG_FILE, json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Геттеры и сеттеры
    public boolean isDeveloperMode() { return developerMode; }
    public void setDeveloperMode(boolean developerMode) { this.developerMode = developerMode; }
    
    public boolean isAutoUpdate() { return autoUpdate; }
    public void setAutoUpdate(boolean autoUpdate) { this.autoUpdate = autoUpdate; }
    
    public String getTheme() { return theme; }
    public void setTheme(String theme) { this.theme = theme; }
    
    public String getDiscordPath() { return discordPath; }
    public void setDiscordPath(String discordPath) { this.discordPath = discordPath; }
    
    public DiscordConfig getDiscordConfig() { return discordConfig; }
    public void setDiscordConfig(DiscordConfig discordConfig) { this.discordConfig = discordConfig; }
}