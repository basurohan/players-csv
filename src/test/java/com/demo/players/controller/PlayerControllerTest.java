package com.demo.players.controller;

import com.demo.players.model.Player;
import com.demo.players.repository.PlayerRepository;
import com.demo.players.service.PlayerService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = PlayerController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class PlayerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PlayerService playerService;

    @MockBean
    PlayerRepository playerRepository;

    List<Player> players = new ArrayList<>();
    Player player;

    @BeforeEach
    void setUp() {
        player = Player.builder()
                .playerID("playerId1")
                .nameFirst("first name")
                .nameLast("last name")
                .build();
        players.add(player);
    }

    @Test
    void findAll() throws Exception {
        when(playerService.findAll()).thenReturn(players);
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/players"));
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].playerID", CoreMatchers.is("playerId1")));

    }

    @Test
    void findById() throws Exception {
        when(playerService.findById("playerId1")).thenReturn(Optional.of(player));
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/players/playerId1"));
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.playerID", CoreMatchers.is("playerId1")));
    }

    @Test
    void findByIdNotFound() throws Exception {
        when(playerService.findById("playerId1")).thenReturn(Optional.empty());
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/players/playerId1"));
        response.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}