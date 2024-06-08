package me.flexicraft;

import me.flexicraft.listeners.JoinLeaveListener;
import org.bukkit.plugin.java.JavaPlugin;

public class FlexiCraft  extends JavaPlugin {

    @Override
    public void onEnable() {
        // Register event Listener
        JoinLeaveListener joinLeaveListener = new JoinLeaveListener(this);
        getServer().getPluginManager().registerEvents(joinLeaveListener, this);

        // Load config.yml
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }
}