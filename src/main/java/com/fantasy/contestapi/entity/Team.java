package com.fantasy.contestapi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String teamName;
    private Long matches = 0L;
    private Long points = 0L;
    private Long wins = 0L;
    private Long loss = 0L;
    private Long noResult = 0L;
    private Double netRunRate = 0.000;
    private Long previousSeasonWins = 0L;
    private Long previousSeasonPoints = 0L;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "homeTeam", fetch = FetchType.LAZY)
    private List<Contest> homeTeamContests = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "awayTeam", fetch = FetchType.LAZY)
    private List<Contest> awayTeamContests = new ArrayList<>();
}
