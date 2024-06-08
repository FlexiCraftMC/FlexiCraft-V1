package me.flexicraft.util;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlexiCraftTabCompleter implements TabCompleter {
    private static final String[] COMMANDS = { "reload", "kick", "fly", "ban", "clear", "unban", "mute", "help", "information" };

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        final List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            List<String> availableCommands = new ArrayList<>();

            for (String cmd : COMMANDS) {
                if (hasPermissionForCommand(sender, cmd)) {
                    availableCommands.add(cmd);
                }
            }

            StringUtil.copyPartialMatches(args[0], availableCommands, completions);
        } else if (args.length == 2) {
            // Implement logic for second argument if needed
        }
        return completions;
    }

    private boolean hasPermissionForCommand(CommandSender sender, String command) {
        switch (command) {
            case "reload":
                return sender.hasPermission("flexicraft.reload");
            case "kick":
                return sender.hasPermission("flexicraft.kick");
            case "fly":
                return sender.hasPermission("flexicraft.fly");
            case "ban":
                return sender.hasPermission("flexicraft.ban");
            case "clear":
                return sender.hasPermission("flexicraft.clear");
            case "unban":
                return sender.hasPermission("flexicraft.unban");
            case "mute":
                return sender.hasPermission("flexicraft.mute");
            case "help":
                return true; // Assume help is available to all
            case "information":
                return true;
            default:
                return false;
        }
    }
}
