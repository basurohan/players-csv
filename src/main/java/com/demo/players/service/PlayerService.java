package com.demo.players.service;

import com.demo.players.model.Player;
import com.demo.players.repository.PlayerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class PlayerService {

    private final PlayerRepository playerRepository;

    public List<Player> findAll() {
        log.debug("Retrieving all players");
        return playerRepository.findAll();
    }

    public Optional<Player> findById(String playerId) {
        log.debug("Retrieving player with id - {}", playerId);
        return playerRepository.findById(playerId);
    }

}
