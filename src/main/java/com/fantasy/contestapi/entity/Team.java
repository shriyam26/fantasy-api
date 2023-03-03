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
    private String points;
    private String wins;
    private String loss;
    private String noResult;
    private String netRunRate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "homeTeam", fetch = FetchType.LAZY)
    private List<Contest> homeTeamContests = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "awayTeam", fetch = FetchType.LAZY)
    private List<Contest> awayTeamContests = new ArrayList<>();
}
