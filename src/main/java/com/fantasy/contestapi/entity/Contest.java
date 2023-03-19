package com.fantasy.contestapi.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Contest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String winnerCount;
    private LocalDateTime matchTime;
    private String contestValue;
    
    @Version
    private Long version;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_team")
    private Team homeTeam;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "away_team")
    private Team awayTeam;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "contest", fetch = FetchType.LAZY)
    private List<Result> results = new ArrayList<>();
}
