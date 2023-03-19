package com.fantasy.contestapi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String playerName;
    private String email;
    private String password;
    private String totalPoints;
    private String wins;
    private String netAmount;
    private Long previousSeasonWins;
    private Long previousSeasonPoints;
    private Long previousSeasonAmount;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "player", fetch = FetchType.LAZY)
    private List<Result> results = new ArrayList<>();
}
