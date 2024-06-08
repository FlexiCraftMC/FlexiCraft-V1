package me.flexicraft.listeners;

import me.flexicraft.FlexiCraft;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinLeaveListener implements Listener {
    private FlexiCraft plugin;

    public JoinLeaveListener(FlexiCraft plugin) { this.plugin = plugin; }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        // Gets the player from the event
        final Player player = event.getPlayer();

        if (player.hasPlayedBefore()){
            event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("join-message").replace("%player%", player.getDisplayName())));
        }else{
            event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("first-time-join-message").replace("%player%", player.getDisplayName())));
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event){
        // Gets the player from the event
        final Player player = event.getPlayer();

        event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("leave-message").replace("%player%", player.getDisplayName())));
    }
}
