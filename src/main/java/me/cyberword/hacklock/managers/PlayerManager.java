package me.cyberword.hacklock.managers;

import me.cyberword.hacklock.objects.HlPlayer;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class PlayerManager {
    private ArrayList<HlPlayer> _players = new ArrayList<>();

    public void addPlayer(HlPlayer player) {
        _players.add(player);
    }

    public Optional<HlPlayer> getPlayerByUuid(UUID uuid) {
        HlPlayer returnedPlayer = null;

        for(HlPlayer player : _players) {
            if(player.getUUID().equals(uuid)) {
                returnedPlayer = player;
            }
        }

        if(returnedPlayer == null) {
            return Optional.empty();
        } else {
            return Optional.of(returnedPlayer);
        }
    }

    public Optional<HlPlayer> getPlayerByName(String name) {
        HlPlayer returnedPlayer = null;

        for(HlPlayer player : _players) {
            if(player.getNickname().equals(name)) {
                returnedPlayer = player;
            }
        }

        if(returnedPlayer == null) {
            return Optional.empty();
        } else {
            return Optional.of(returnedPlayer);
        }
    }

    public void removePlayer(HlPlayer player) {
        _players.removeIf(hp -> hp.equals(player));
    }
}
