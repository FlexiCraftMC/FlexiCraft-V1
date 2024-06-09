package me.flexicraft;

import me.flexicraft.commands.*;
import me.flexicraft.listeners.JoinLeaveListener;
import me.flexicraft.util.FlexiCraftTabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

public class FlexiCraft  extends JavaPlugin {

    @Override
    public void onEnable() {

        // Register commands
        getCommand("flexicraft").setTabCompleter(new FlexiCraftTabCompleter());
        getCommand("flexicraft").setExecutor(new FlexiRoot(this));

        getCommand("ban").setExecutor(new Ban(this));
        getCommand("unban").setExecutor(new Unban(this));
        getCommand("fly").setExecutor(new Fly(this));
        getCommand("clear").setExecutor(new Clear(this));

        // Register event Listener
        JoinLeaveListener joinLeaveListener = new JoinLeaveListener(this);
        getServer().getPluginManager().registerEvents(joinLeaveListener, this);

        // Load config.yml
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }
}