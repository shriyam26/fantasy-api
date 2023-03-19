package com.fantasy.contestapi.repository;

import com.fantasy.contestapi.entity.Contest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ContestRepository extends JpaRepository<Contest, Long> {

    Contest findContestByHomeTeam_IdAndAwayTeam_Id(Long homeTeam, Long awayTeam);

    Contest findContestByMatchTimeEquals(LocalDateTime matchTime);

    List<Contest> findAllByIdIn(List<Long> ids);
}
