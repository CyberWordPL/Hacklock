package me.cyberword.hacklock.listeners;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerRotation;
import me.cyberword.hacklock.Hacklock;
import me.cyberword.hacklock.objects.HlPlayer;
import org.bukkit.entity.Player;

import java.util.Optional;

public class InvalidPitchPacketListener implements PacketListener {
    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.PLAYER_ROTATION) {
            WrapperPlayClientPlayerRotation packet = new WrapperPlayClientPlayerRotation(event);
            float pitch = packet.getPitch();
            if(pitch > 90.0F || pitch < -90.0F) {
                Player bukkitPlayer = event.getPlayer();
                Optional<HlPlayer> optionalHlPlayer = Hacklock.playerManager.getPlayerByUuid(bukkitPlayer.getUniqueId());
                if(optionalHlPlayer.isEmpty()) {
                    throw new IllegalStateException("Player isn't in player manager but packet from that player was received. Report this to https://github.com/CyberWordPL/Hacklock");
                }
                HlPlayer hlPlayer = optionalHlPlayer.get();

                if(hlPlayer.canBypass()) return;

                Hacklock.messageManager.getPlayerDetectionMessages().sendCheatDetectedToAdminMessage("Invalid Pitch", hlPlayer);

                event.setCancelled(true);
                hlPlayer.removePlayer("Kill Aura Detected!!!");
            }
        }
    }
}