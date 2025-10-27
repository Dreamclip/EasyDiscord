package com.easydiscord.launcher.config;

public class DiscordConfig {
    private boolean developerMode = false;
    private boolean disableHardwareAcceleration = false;
    private boolean customCSS = true;
    private boolean removeGiftButton = true;
    private boolean removeNitroButton = true;
    private boolean compactMode = false;
    private boolean transparentWindow = false;
    private String customTheme = "default";

    // Геттеры и сеттеры
    public boolean isDeveloperMode() { return developerMode; }
    public void setDeveloperMode(boolean developerMode) { this.developerMode = developerMode; }

    public boolean isDisableHardwareAcceleration() { return disableHardwareAcceleration; }
    public void setDisableHardwareAcceleration(boolean disable) { this.disableHardwareAcceleration = disable; }

    public boolean isCustomCSS() { return customCSS; }
    public void setCustomCSS(boolean customCSS) { this.customCSS = customCSS; }

    public boolean isRemoveGiftButton() { return removeGiftButton; }
    public void setRemoveGiftButton(boolean remove) { this.removeGiftButton = remove; }

    public boolean isRemoveNitroButton() { return removeNitroButton; }
    public void setRemoveNitroButton(boolean remove) { this.removeNitroButton = remove; }

    public boolean isCompactMode() { return compactMode; }
    public void setCompactMode(boolean compactMode) { this.compactMode = compactMode; }

    public boolean isTransparentWindow() { return transparentWindow; }
    public void setTransparentWindow(boolean transparent) { this.transparentWindow = transparent; }

    public String getCustomTheme() { return customTheme; }
    public void setCustomTheme(String theme) { this.customTheme = theme; }
}