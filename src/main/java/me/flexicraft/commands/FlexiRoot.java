package me.flexicraft.commands;

import me.flexicraft.FlexiCraft;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.PluginCommandYamlParser;
import org.bukkit.entity.Player;

import java.util.List;
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
        StringBuilder helpMessage = new StringBuilder(ChatColor.GREEN + "====== " + ChatColor.GOLD + "FlexiCraft Help" + ChatColor.GREEN + " ======\n");

        List<Command> commands = PluginCommandYamlParser.parse(plugin);
        for (Command cmd : commands) {
            if (!(cmd instanceof PluginCommand)) continue;
            PluginCommand pluginCommand = (PluginCommand) cmd;
            if (pluginCommand.getPlugin() != plugin) continue; // Only show commands of this plugin

            String permission = pluginCommand.getPermission();
            if (permission == null || sender.hasPermission(permission)) {
                String description = pluginCommand.getDescription();
                helpMessage.append(String.format("§e%s §7- %s\n", pluginCommand.getUsage(), description));
            }
        }

        sender.sendMessage(helpMessage.toString());
        return true;
    }
}


