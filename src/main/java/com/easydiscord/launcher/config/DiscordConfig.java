// DiscordConfig.java
package com.easydiscord.launcher.config;

public class DiscordConfig {
    private boolean developerMode;
    private boolean disableHardwareAcceleration;
    private boolean customCssEnabled;
    private String customCss;
    private boolean removeClutter;
    private boolean compactMode;
    private boolean experimentalFeatures;
    
    public DiscordConfig() {
        this.developerMode = false;
        this.disableHardwareAcceleration = false;
        this.customCssEnabled = false;
        this.customCss = "";
        this.removeClutter = true;
        this.compactMode = false;
        this.experimentalFeatures = false;
    }
    
    // Геттеры и сеттеры
    public boolean isDeveloperMode() { return developerMode; }
    public void setDeveloperMode(boolean developerMode) { this.developerMode = developerMode; }
    
    public boolean isDisableHardwareAcceleration() { return disableHardwareAcceleration; }
    public void setDisableHardwareAcceleration(boolean disableHardwareAcceleration) { 
        this.disableHardwareAcceleration = disableHardwareAcceleration; 
    }
    
    public boolean isCustomCssEnabled() { return customCssEnabled; }
    public void setCustomCssEnabled(boolean customCssEnabled) { this.customCssEnabled = customCssEnabled; }
    
    public String getCustomCss() { return customCss; }
    public void setCustomCss(String customCss) { this.customCss = customCss; }
    
    public boolean isRemoveClutter() { return removeClutter; }
    public void setRemoveClutter(boolean removeClutter) { this.removeClutter = removeClutter; }
    
    public boolean isCompactMode() { return compactMode; }
    public void setCompactMode(boolean compactMode) { this.compactMode = compactMode; }
    
    public boolean isExperimentalFeatures() { return experimentalFeatures; }
    public void setExperimentalFeatures(boolean experimentalFeatures) { this.experimentalFeatures = experimentalFeatures; }
}