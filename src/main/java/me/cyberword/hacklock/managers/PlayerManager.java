package me.cyberword.hacklock.managers;

import me.cyberword.hacklock.objects.HlPlayer;
import net.kyori.adventure.text.Component;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class PlayerManager {
    private ArrayList<HlPlayer> _players = new ArrayList<>();
    private ArrayList<HlPlayer> _adminPlayers = new ArrayList<>();

    public void addPlayer(HlPlayer player) {
        _players.add(player);

        if(player.isAdmin()) {
            _adminPlayers.add(player);
        }
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
        _adminPlayers.removeIf(hp -> hp.equals(player));
    }

    public ArrayList<HlPlayer> getPlayers() {
        return _players;
    }

    public ArrayList<HlPlayer> getAdminPlayers() {
        return _adminPlayers;
    }
}
