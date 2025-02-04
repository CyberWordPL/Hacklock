package me.cyberword.hacklock.commands;

import me.cyberword.hacklock.Hacklock;
import me.cyberword.hacklock.objects.HlPlayer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class PermanentlyRemovePlayerCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        /*if(sender instanceof Player) {
            if(!sender.hasPermission("hacklock.permremove")) return false;
        }*/

        Optional<HlPlayer> optionalPlayer = Hacklock.playerManager.getPlayerByName(args[0]);
        if(optionalPlayer.isEmpty()) {
            sender.sendMessage(Component.text("Player (" + args[0] + " ) is not online/does not exist").color(TextColor.fromHexString("A02020")));
            return false;
        }

        HlPlayer player = optionalPlayer.get();
        player.permanentlyRemovePlayer(args[1]);

        return true;
    }
}
