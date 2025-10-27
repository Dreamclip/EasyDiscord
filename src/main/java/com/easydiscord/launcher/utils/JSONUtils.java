package com.easydiscord.launcher.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.lang.reflect.Type;

public class JSONUtils {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static <T> T loadFromFile(String filePath, Class<T> classOfT) {
        try {
            if (!FileUtils.fileExists(filePath)) {
                return classOfT.getDeclaredConstructor().newInstance();
            }
            String content = FileUtils.readFile(filePath);
            return gson.fromJson(content, classOfT);
        } catch (Exception e) {
            System.err.println("Ошибка загрузки конфига: " + e.getMessage());
            try {
                return classOfT.getDeclaredConstructor().newInstance();
            } catch (Exception ex) {
                return null;
            }
        }
    }

    public static void saveToFile(String filePath, Object object) {
        try {
            String json = gson.toJson(object);
            FileUtils.writeFile(filePath, json);
        } catch (Exception e) {
            System.err.println("Ошибка сохранения конфига: " + e.getMessage());
        }
    }
}