package me.cyberword.hacklock.objects;

import me.cyberword.hacklock.Hacklock;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;

import java.util.UUID;

// PlayerManager.getPlayerByName("Test1").get().removePlayer("Test1");

public class HlPlayer {
    private Player _player;

    public HlPlayer(Player player) {
        _player = player;
    }

    public String getNickname() {
        return _player.getName();
    }

    public UUID getUUID() {
        return _player.getUniqueId();
    }

    public boolean isAdmin() {
        return _player.hasPermission("hacklock.admin");
    }

    public boolean canBypass() {
        return _player.hasPermission("hacklock.bypass");
    }

    public void removePlayer(String reason) {
        _player.kick(Component.text("[HACKLOCK]\nYou are removed (kicked) from the server.\nReason: " + reason).color(TextColor.fromHexString("#FC3D03")));
        Hacklock.messageManager.getPlayerDetectionMessages().sendPlayerRemovedToAdminMessage(this);
    }

    public void permanentlyRemovePlayer(String reason) {
        _player.kick(Component.text("[HACKLOCK]\nYou are permanently removed (banned) from the server.\nReason: " + reason).color(TextColor.fromHexString("#A00000")));
        _player.banPlayer(reason);
        Hacklock.messageManager.getPlayerDetectionMessages().sendPlayerPermanentlyRemovedToAdminMessage(this);
    }

    public void sendMessage(Component message) {
        _player.sendMessage(message);
    }
}
