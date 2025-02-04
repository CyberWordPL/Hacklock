package me.cyberword.hacklock.objects;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

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

    public void removePlayer(String reason) {
        _player.kick(Component.text("[HACKLOCK]\nYou are removed (kicked) from  the server.\nReason: " + reason).color(TextColor.fromHexString("#A00000")));
    }

    public void permanentlyRemovePlayer(String reason) {
        _player.kick(Component.text("[HACKLOCK]\nYou are permanently removed (banned) from the server.\nReason: " + reason).color(TextColor.fromHexString("#A00000")));
        _player.banPlayer(reason);
    }

    void showMessage(String message) {
        _player.sendMessage(Component.text("[HACKLOCK] " + message).color(TextColor.fromHexString("#A00000")));
    }
}
