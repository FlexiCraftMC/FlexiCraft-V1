package me.flexicraft.commands;

import me.flexicraft.FlexiCraft;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlexiRoot implements CommandExecutor {

    private final FlexiCraft plugin;

    public FlexiRoot(FlexiCraft plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length >= 1 && args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("flexicraft.reload")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("config-reload-no-perms")));
                return true;
            }

            plugin.reloadConfig();
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("config-reload")));
            return true;
        }

        if (args.length >= 1 && args[0].equalsIgnoreCase("information")) {
            Player player = (Player) sender;
            player.sendMessage(ChatColor.GREEN + "======= " + ChatColor.GOLD + "FlexiCraft Information" + ChatColor.GREEN + " =======");
            player.sendMessage(ChatColor.YELLOW + "Plugin Name: " + ChatColor.WHITE + "FlexiCraft");
            player.sendMessage(ChatColor.YELLOW + "Plugin Version: " + ChatColor.WHITE + plugin.getDescription().getVersion());
            player.sendMessage(ChatColor.YELLOW + "Plugin Description: " + ChatColor.WHITE + plugin.getDescription().getDescription());
            player.sendMessage(ChatColor.YELLOW + "Contributors: " + ChatColor.WHITE + String.join(", ", plugin.getDescription().getAuthors()));
            return true;
        }


        // Display help information when no arguments or "help" are provided
        sender.sendMessage(ChatColor.GREEN + "======= " + ChatColor.GOLD + "FlexiCraft Help" + ChatColor.GREEN + " =======");
        sender.sendMessage(ChatColor.YELLOW + "/flexicraft reload" + ChatColor.GRAY + " - Reload the config file.");
        sender.sendMessage(ChatColor.YELLOW + "/ban <player> [reason]" + ChatColor.GRAY + " - Ban a player from the server.");
        sender.sendMessage(ChatColor.YELLOW + "/unban <player>" + ChatColor.GRAY + " - Unban a player from the ban list.");
        sender.sendMessage(ChatColor.YELLOW + "/kick <player> [reason]" + ChatColor.GRAY + " - Kick a player from the server.");
        sender.sendMessage(ChatColor.YELLOW + "/mute <player>" + ChatColor.GRAY + " - Mute a player from the chat.");
        sender.sendMessage(ChatColor.YELLOW + "/unmute <player>" + ChatColor.GRAY + " - Unmute a mob in the chat.");
        sender.sendMessage(ChatColor.YELLOW + "/fly <player>" + ChatColor.GRAY + " - Toggle fly mode for yourself.");
        sender.sendMessage(ChatColor.YELLOW + "/clear <player>" + ChatColor.GRAY + " - Clear your inventory.");
        sender.sendMessage(ChatColor.YELLOW + "/repair" + ChatColor.GRAY + " - Repair an item that is broken.");
        return true;

    }
}


