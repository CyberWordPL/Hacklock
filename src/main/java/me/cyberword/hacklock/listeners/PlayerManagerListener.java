package me.cyberword.hacklock.listeners;

import me.cyberword.hacklock.Hacklock;
import me.cyberword.hacklock.objects.HlPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerManagerListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Hacklock.playerManager.addPlayer(new HlPlayer(event.getPlayer()));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        // Ignore that this would break if player does not exist. but it should exist :LIKE:
        // but who cares
        HlPlayer player = Hacklock.playerManager.getPlayerByUuid(event.getPlayer().getUniqueId()).get();
        Hacklock.playerManager.removePlayer(player);
    }
}
