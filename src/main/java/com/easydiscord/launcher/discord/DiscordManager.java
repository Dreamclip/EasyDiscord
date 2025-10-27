package com.easydiscord.launcher.discord;

import com.easydiscord.launcher.config.DiscordConfig;
import com.easydiscord.launcher.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DiscordManager {

    public static boolean launchDiscord(String discordPath, DiscordConfig config) {
        try {
            String executable = findDiscordExecutable(discordPath);
            if (executable.isEmpty()) {
                return false;
            }

            List<String> command = new ArrayList<>();
            command.add(executable);

            // Добавляем аргументы на основе конфигурации
            if (config.isDisableHardwareAcceleration()) {
                command.add("--disable-hardware-acceleration");
            }

            if (config.isDeveloperMode()) {
                command.add("--enable-developer-mode");
            }

            // Дополнительные аргументы
            command.add("--disable-features=HardwareMediaKeyHandling");
            command.add("--disable-background-timer-throttling");

            ProcessBuilder pb = new ProcessBuilder(command);
            pb.directory(new File(discordPath));

            // Применяем кастомный CSS если включено
            if (config.isCustomCSS()) {
                applyCustomCSS(config);
            }

            Process process = pb.start();
            return process.isAlive();

        } catch (IOException e) {
            System.err.println("Ошибка запуска Discord: " + e.getMessage());
            return false;
        }
    }

    private static String findDiscordExecutable(String discordPath) {
        String[] possibleExecutables = {
                "Discord.exe",
                "Update.exe", // Discord часто запускается через Update.exe
                "Discord",
                "Discord.app/Contents/MacOS/Discord"
        };

        for (String exe : possibleExecutables) {
            String fullPath = discordPath + File.separator + exe;
            if (FileUtils.fileExists(fullPath)) {
                return fullPath;
            }
        }
        return "";
    }

    private static void applyCustomCSS(DiscordConfig config) {
        // Здесь будет логика применения кастомного CSS
        // Пока что это заглушка
        System.out.println("Применение кастомного CSS...");

        String cssContent = generateCSS(config);
        // В будущем здесь будет инжектирование CSS в Discord
    }

    private static String generateCSS(DiscordConfig config) {
        StringBuilder css = new StringBuilder();

        if (config.isRemoveGiftButton()) {
            css.append("/* Скрытие кнопки подарков */\n")
                    .append("button[aria-label*=\"gift\"], button[aria-label*=\"Gift\"] { display: none !important; }\n");
        }

        if (config.isRemoveNitroButton()) {
            css.append("/* Скрытие кнопки Nitro */\n")
                    .append("button[aria-label*=\"nitro\"], button[aria-label*=\"Nitro\"] { display: none !important; }\n");
        }

        if (config.isCompactMode()) {
            css.append("/* Компактный режим */\n")
                    .append(".membersWrap-2h-GB4 { min-width: 60px !important; }\n")
                    .append(".container-1D34oG { padding: 2px !important; }\n");
        }

        if (config.isTransparentWindow()) {
            css.append("/* Прозрачное окно */\n")
                    .append(".app-2CXKsg { background: transparent !important; }\n");
        }

        return css.toString();
    }
}