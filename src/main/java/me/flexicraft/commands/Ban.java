package me.flexicraft.commands;

import me.flexicraft.FlexiCraft;
import org.bukkit.Bukkit;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Ban implements CommandExecutor {

    private final FlexiCraft plugin;
    private final String banPermission = "flexicraft.ban";

    public Ban(FlexiCraft plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("no-player-error")));
            return true;
        }

        Player player = (Player) sender;

        // Check permissions
        if (!player.hasPermission(banPermission)) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ban-no-perms")));
            return true;
        }

        if (args.length < 1) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ban-usage")));
            return true;
        }

        String playerName = args[0];
        Player targetPlayer = Bukkit.getPlayer(playerName);
        if (targetPlayer == null) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ban-no-player")));
            return true;
        }

        // Ban message and banning
        String[] reasonArgs = new String[args.length - 1];
        System.arraycopy(args, 1, reasonArgs, 0, args.length - 1);
        String reason = String.join(" ", reasonArgs);

        Bukkit.getBanList(org.bukkit.BanList.Type.NAME).addBan(targetPlayer.getName(), reason, null, null);
        targetPlayer.kickPlayer(reason);

        String banMessage = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ban-server-message"))
                .replace("%target-player%", targetPlayer.getName())
                .replace("%reason%", reason);
        Bukkit.broadcastMessage(banMessage);

        return true;
    }
}
