package me.cyberword.hacklock.listeners;

import me.cyberword.hacklock.Hacklock;
import me.cyberword.hacklock.detection.KillAuraChecks;
import me.cyberword.hacklock.objects.HlPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Optional;

public class AntiKillAuraListener implements Listener {
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player attacker) {
            Entity attacked = event.getEntity();

            boolean checkIfValid = KillAuraChecks.checkIfHitIsValid(attacker, attacked);

            if(!checkIfValid) {
                Optional<HlPlayer> hacklockPlayerOptional = Hacklock.playerManager.getPlayerByUuid(attacker.getUniqueId());

                if(hacklockPlayerOptional.isEmpty()) {
                    throw new IllegalArgumentException("Player is not in player manager but player damaged entity. Report this to https://github.com/CyberWordPL/Hacklock");
                }

                HlPlayer hacklockPlayer = hacklockPlayerOptional.get();

                if(hacklockPlayer.canBypass()) return;

                event.setCancelled(true);
                hacklockPlayer.removePlayer("Kill Aura Detected!!!");

                Hacklock.messageManager.getPlayerDetectionMessages().sendCheatDetectedToAdminMessage("Kill Aura", hacklockPlayer);
            }
        }
    }
}
