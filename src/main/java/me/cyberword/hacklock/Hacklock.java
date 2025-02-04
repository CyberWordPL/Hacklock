package me.cyberword.hacklock;

import me.cyberword.hacklock.commands.PermanentlyRemovePlayerCommand;
import me.cyberword.hacklock.commands.RemovePlayerCommand;
import me.cyberword.hacklock.listeners.PlayerManagerListener;
import me.cyberword.hacklock.managers.PlayerManager;
import me.cyberword.hacklock.objects.HlPlayer;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Hacklock extends JavaPlugin {
    public static PlayerManager playerManager;

    @Override
    public void onEnable() {
        // Load Managers
        playerManager = new PlayerManager();

        // Load Listeners & Events
        getServer().getPluginManager().registerEvents(new PlayerManagerListener(), this);

        // Get players and add players into Player Manager
        Bukkit.getOnlinePlayers().forEach(player -> playerManager.addPlayer(new HlPlayer(player)));

        // Load Commands
        this.getCommand("remove").setExecutor(new RemovePlayerCommand());
        this.getCommand("permremove").setExecutor(new PermanentlyRemovePlayerCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
