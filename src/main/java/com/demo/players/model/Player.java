package com.demo.players.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "players")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    @Id
    @Column(name = "player_id")
    private String playerID;
    @Column(name = "birth_year")
    private Integer birthYear;
    @Column(name = "birth_month")
    private Integer birthMonth;
    @Column(name = "birth_day")
    private Integer birthDay;
    @Column(name = "birth_country")
    private String birthCountry;
    @Column(name = "birth_state")
    private String birthState;
    @Column(name = "birth_city")
    private String birthCity;
    @Column(name = "death_year")
    private Integer deathYear;
    @Column(name = "death_month")
    private Integer deathMonth;
    @Column(name = "death_day")
    private Integer deathDay;
    @Column(name = "death_country")
    private String deathCountry;
    @Column(name = "death_state")
    private String deathState;
    @Column(name = "death_city")
    private String deathCity;
    @Column(name = "name_first")
    private String nameFirst;
    @Column(name = "name_last")
    private String nameLast;
    @Column(name = "name_given")
    private String nameGiven;
    @Column(name = "weight")
    private Integer weight;
    @Column(name = "height")
    private Integer height;
    @Column(name = "bats")
    private String bats;
    @Column(name = "throws")
    @JsonProperty("throws")
    private String throwing_arm;
    @Column(name = "debut")
    private String debut;
    @Column(name = "final_game")
    private String finalGame;
    @Column(name = "retro_id")
    private String retroID;
    @Column(name = "bbref_id")
    private String bbrefID;
}
