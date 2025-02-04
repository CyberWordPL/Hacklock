package me.cyberword.hacklock.listeners;

import me.cyberword.hacklock.Hacklock;
import me.cyberword.hacklock.detection.AntiKillAuraChecks;
import me.cyberword.hacklock.managers.PlayerManager;
import me.cyberword.hacklock.objects.HlPlayer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.text.Format;
import java.util.Optional;

public class AntiKillAuraListener implements Listener {
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player attacker = (Player) event.getDamager();
            Entity attacked = event.getEntity();

            boolean checkIfValid = AntiKillAuraChecks.checkIfHitIsValid(attacker, attacked);

            if(!checkIfValid) {
                Optional<HlPlayer> hacklockPlayerOptional = Hacklock.playerManager.getPlayerByUuid(attacker.getUniqueId());

                if(hacklockPlayerOptional.isEmpty()) {
                    throw new IllegalArgumentException("Player is not in player manager but player damaged entity. Report this to https://github.com/CyberWordPL/Hacklock");
                }

                HlPlayer hacklockPlayer = hacklockPlayerOptional.get();

                if(hacklockPlayer.canBypass()) return;

                event.setCancelled(true);
                hacklockPlayer.removePlayer("Kill Aura Detected!!!");

                String templateString = "Player %s was detected with killaura, angle of attack was %f";

                Hacklock.playerManager.sendMessageToAdmins(Component.text(templateString.formatted(attacker.getName(), AntiKillAuraChecks.getAngle(attacker, attacked))).color(TextColor.fromHexString("#10A0FF")));
            }
        }
    }
}
