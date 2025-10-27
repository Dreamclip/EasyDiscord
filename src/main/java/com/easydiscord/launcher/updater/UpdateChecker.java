package com.easydiscord.launcher.updater;

import javax.swing.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class UpdateChecker {
    private static final String GITHUB_REPO = "Dreamclip/EasyDiscord";

    public static void checkForUpdates(boolean autoUpdate) {
        if (!autoUpdate) return;

        try {
            URL url = new URL("https://api.github.com/repos/" + GITHUB_REPO + "/releases/latest");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                // Здесь будет парсинг JSON ответа для получения информации об обновлении
                System.out.println("Проверка обновлений...");
                // В будущем здесь будет логика скачивания обновлений
            }

        } catch (IOException e) {
            System.err.println("Ошибка проверки обновлений: " + e.getMessage());
        }
    }
}