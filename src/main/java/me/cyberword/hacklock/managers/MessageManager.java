package me.cyberword.hacklock.managers;

import me.cyberword.hacklock.messages.PlayerDetectionMessages;

public class MessageManager {
    private PlayerDetectionMessages _playerDetectionMessages;

    public MessageManager() {
        _playerDetectionMessages = new PlayerDetectionMessages();
    }

    public PlayerDetectionMessages getPlayerDetectionMessages() {
        return _playerDetectionMessages;
    }
}
