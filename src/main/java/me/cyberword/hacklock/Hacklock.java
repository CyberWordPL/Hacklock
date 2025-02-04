package me.cyberword.hacklock;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import me.cyberword.hacklock.commands.PermanentlyRemovePlayerCommand;
import me.cyberword.hacklock.commands.RemovePlayerCommand;
import me.cyberword.hacklock.listeners.AntiKillAuraListener;
import me.cyberword.hacklock.listeners.PlayerManagerListener;
import me.cyberword.hacklock.managers.PlayerManager;
import me.cyberword.hacklock.objects.HlPlayer;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Hacklock extends JavaPlugin {
    public static FileConfiguration configuration;
    public static Hacklock instance;
    public static ProtocolManager protocolManager;
    public static PlayerManager playerManager;

    @Override
    public void onEnable() {
        instance = this;

        // Load config
        configuration = getConfig();
        configuration.options().copyDefaults(true);
        saveConfig();

        // Load Managers
        playerManager = new PlayerManager();
        protocolManager = ProtocolLibrary.getProtocolManager();

        // Load Listeners & Events
        getServer().getPluginManager().registerEvents(new PlayerManagerListener(), this);
        getServer().getPluginManager().registerEvents(new AntiKillAuraListener(), this);

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
