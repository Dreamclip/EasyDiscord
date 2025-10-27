package com.easydiscord.launcher.utils;

import java.io.*;
import java.nio.file.*;

public class FileUtils {

    public static void createDirectory(String path) {
        try {
            Files.createDirectories(Paths.get(path));
        } catch (IOException e) {
            System.err.println("Ошибка создания директории: " + e.getMessage());
        }
    }

    public static boolean fileExists(String path) {
        return Files.exists(Paths.get(path));
    }

    public static void writeFile(String path, String content) throws IOException {
        Files.write(Paths.get(path), content.getBytes());
    }

    public static String readFile(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }

    public static String findDiscordPath() {
        String[] possiblePaths = {
                System.getenv("LOCALAPPDATA") + "\\Discord",
                System.getenv("PROGRAMFILES") + "\\Discord",
                System.getenv("PROGRAMFILES(X86)") + "\\Discord",
                "/usr/bin/discord",
                "/Applications/Discord.app/Contents/MacOS"
        };

        for (String path : possiblePaths) {
            if (fileExists(path)) {
                return path;
            }
        }
        return "";
    }
}