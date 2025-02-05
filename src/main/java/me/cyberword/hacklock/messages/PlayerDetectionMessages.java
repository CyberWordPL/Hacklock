package me.cyberword.hacklock.messages;

import me.cyberword.hacklock.Hacklock;
import me.cyberword.hacklock.objects.HlPlayer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class PlayerDetectionMessages {
    public void sendCheatDetectedToAdminMessage(String cheatName, HlPlayer detectedPlayer) {
        Component finalMessage = Component.text("[HACKLOCK] ", TextColor.fromHexString("#1030A0"));
        String formatString = "Player %s (%s) was detected with %s";
        String formattedString = String.format(formatString, detectedPlayer.getNickname(), detectedPlayer.getUUID(), cheatName);

        Component formatedComponent = Component.text(formattedString, TextColor.fromHexString("#3070FF"));
        finalMessage = finalMessage.append(formatedComponent);

        for(HlPlayer player : Hacklock.playerManager.getAdminPlayers()) {
            player.sendMessage(finalMessage);
        }
    }

    public void sendPlayerRemovedToAdminMessage(HlPlayer detectedPlayer) {
        Component finalMessage = Component.text("[HACKLOCK] ", TextColor.fromHexString("#FF5D03"));
        String formatString = "Player %s (%s) was removed";
        String formattedString = String.format(formatString, detectedPlayer.getNickname(), detectedPlayer.getUUID());

        Component formatedComponent = Component.text(formattedString, TextColor.fromHexString("#FC3D03"));
        finalMessage = finalMessage.append(formatedComponent);

        for(HlPlayer player : Hacklock.playerManager.getAdminPlayers()) {
            player.sendMessage(finalMessage);
        }
    }

    public void sendPlayerPermanentlyRemovedToAdminMessage(HlPlayer detectedPlayer) {
        Component finalMessage = Component.text("[HACKLOCK] ", TextColor.fromHexString("#A00000"));
        String formatString = "Player %s (%s) was permanently removed";
        String formattedString = String.format(formatString, detectedPlayer.getNickname(), detectedPlayer.getUUID());

        Component formatedComponent = Component.text(formattedString, TextColor.fromHexString("#FF0000"));
        finalMessage = finalMessage.append(formatedComponent);

        for(HlPlayer player : Hacklock.playerManager.getAdminPlayers()) {
            player.sendMessage(finalMessage);
        }
    }
}
