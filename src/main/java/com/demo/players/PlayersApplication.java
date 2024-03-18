package com.demo.players;

import com.demo.players.model.Player;
import com.demo.players.repository.PlayerRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Players API", version = "1.0", description = "Players API Microservice"))
@RequiredArgsConstructor
@Slf4j
public class PlayersApplication {

	private final PlayerRepository playerRepository;

	public static void main(String[] args) {
		SpringApplication.run(PlayersApplication.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {
			log.debug("Loading data...");

			BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Player.csv"));
			String line = "";
			List<Player> players = new ArrayList<>();
			br.readLine();
			while((line=br.readLine()) != null) {
				String[] data = line.split(",");
				try {
					Player player = Player.builder()
							.playerID(data[0])
							.birthYear(!(data[1]).isEmpty() ? Integer.parseInt(data[1]) : null)
							.birthMonth(!(data[2]).isEmpty() ? Integer.parseInt(data[2]) : null)
							.birthDay(!(data[3]).isEmpty() ? Integer.parseInt(data[3]) : null)
							.birthCountry(!(data[4]).isEmpty() ? data[4] : null)
							.birthState(!(data[5]).isEmpty() ? data[5] : null)
							.birthCity(!(data[6]).isEmpty() ? data[6] : null)
							.deathYear(!(data[7]).isEmpty() ? Integer.parseInt(data[7]) : null )
							.deathMonth(!(data[8]).isEmpty() ? Integer.parseInt(data[8]) : null)
							.deathDay(!(data[9]).isEmpty() ? Integer.parseInt(data[9]) : null)
							.deathCountry(!(data[10]).isEmpty() ? data[10] : null)
							.deathState(!(data[11]).isEmpty() ? data[11] : null)
							.deathCity(!(data[12]).isEmpty() ? data[12] : null)
							.nameFirst(!(data[13]).isEmpty() ? data[13] : null)
							.nameLast(!(data[14]).isEmpty() ? data[14] : null)
							.nameGiven(!(data[15]).isEmpty() ? data[15] : null)
							.weight(!(data[16]).isEmpty() ? Integer.parseInt(data[16]) : null)
							.height(!(data[17]).isEmpty() ? Integer.parseInt(data[17]) : null)
							.bats(!(data[18]).isEmpty() ? data[18] : null)
							.throwing_arm(!(data[19]).isEmpty() ? data[19] : null)
							.debut(!(data[20]).isEmpty() ? data[20] : null)
							.finalGame(!(data[21]).isEmpty() ? data[21] : null)
							.retroID(!(data[22]).isEmpty() ? data[22] : null)
							.bbrefID(!(data[23]).isEmpty() ? data[23] : null)
							.build();

					players.add(player);
				} catch (Exception e) {
					log.error("Skipping row due to exception - " + e.getMessage());
				}
			}
			File file = new File("src/main/resources/People.csv");
			PrintWriter out = new PrintWriter(file);

			players.forEach(pl -> {
				out.printf("%s,%s\n", pl.getPlayerID(), pl.getNameFirst());
			});

			out.close();
			playerRepository.saveAll(players);
		};
	}

}
