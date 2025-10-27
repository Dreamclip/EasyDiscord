// DiscordStarter.java
package com.easydiscord.launcher.utils;

import com.easydiscord.launcher.config.LauncherConfig;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DiscordStarter {
    
    public static boolean launchDiscord(LauncherConfig config) {
        try {
            String command = buildLaunchCommand(config);
            
            ProcessBuilder processBuilder = new ProcessBuilder();
            
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                processBuilder.command("cmd.exe", "/c", command);
            } else {
                processBuilder.command("sh", "-c", command);
            }
            
            processBuilder.directory(new File(System.getProperty("user.home")));
            Process process = processBuilder.start();
            
            // Записываем настройки в файл для модифицированного клиента
            writeDiscordConfig(config);
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    private static String buildLaunchCommand(LauncherConfig config) {
        List<String> args = new ArrayList<>();
        
        // Базовый путь к Discord
        String discordPath = config.getDiscordPath();
        
        // Добавляем аргументы запуска
        if (config.getDiscordConfig().isDisableHardwareAcceleration()) {
            args.add("--disable-hardware-acceleration");
        }
        
        if (config.getDiscordConfig().isDeveloperMode()) {
            args.add("--enable-developer-mode");
        }
        
        if (config.getDiscordConfig().isExperimentalFeatures()) {
            args.add("--enable-experimental-ui");
        }
        
        // Аргумент для загрузки кастомного CSS
        if (config.getDiscordConfig().isCustomCssEnabled()) {
            String cssFile = new File("config/custom.css").getAbsolutePath();
            args.add("--load-stylesheet=" + cssFile);
        }
        
        // Собираем полную команду
        StringBuilder command = new StringBuilder("\"" + discordPath + "\"");
        for (String arg : args) {
            command.append(" ").append(arg);
        }
        
        return command.toString();
    }
    
    private static void writeDiscordConfig(LauncherConfig config) throws IOException {
        File configDir = new File("config");
        if (!configDir.exists()) {
            configDir.mkdirs();
        }
        
        // Записываем кастомный CSS если включен
        if (config.getDiscordConfig().isCustomCssEnabled() && 
            !config.getDiscordConfig().getCustomCss().isEmpty()) {
            
            File cssFile = new File("config/custom.css");
            FileUtils.writeFile(cssFile, config.getDiscordConfig().getCustomCss());
        }
        
        // Записываем настройки для модифицированного клиента
        File discordConfig = new File("config/discord_settings.json");
        com.google.gson.Gson gson = new com.google.gson.GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(config.getDiscordConfig());
        FileUtils.writeFile(discordConfig, json);
    }
    
    public static String getLaunchCommand(LauncherConfig config) {
        return buildLaunchCommand(config);
    }
}