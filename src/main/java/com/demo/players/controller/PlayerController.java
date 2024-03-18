package com.demo.players.controller;

import com.demo.players.model.Player;
import com.demo.players.service.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Tag(name = "Players", description = "Players APIs")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class PlayerController {

    private final PlayerService playerService;

    @Operation(summary = "Returns the list of all players")
    @ApiResponse(responseCode = "200", description = "Returns the list of all players",
            content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Player.class)))})
    @GetMapping("/players")
    public ResponseEntity<List<Player>> findAll() {
        log.debug("Processing request to retrieve all players");
        return ResponseEntity.ok(playerService.findAll());
    }

    @Operation(summary = "Returns a single player by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns a single player by id.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Player.class))}),
            @ApiResponse(responseCode = "404", description = "Not found", content = {@Content})
    })
    @GetMapping("/players/{playerID}")
    public ResponseEntity<Player> findById(@PathVariable final String playerID) {
        log.debug("Processing request to retrieve player by player id");

        Optional<Player> response = playerService.findById(playerID);
        return response.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }
}
