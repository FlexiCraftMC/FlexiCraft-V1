package me.flexicraft;

import org.bukkit.plugin.java.JavaPlugin;

public class FlexiCraft  extends JavaPlugin {
    @Override
    public void onLoad() {
        // 1.
        // This method will be called when the plugin is being loaded, but not yet enabled.
    }

    @Override
    public void onEnable() {
        // 2.
        // This method will be called when the plugin is enabled.
        getLogger().info("Plugin has been enabled");
    }

    @Override
    public void onDisable() {
        // 3.
        // This method will be called when the server shuts down.
    }
}
