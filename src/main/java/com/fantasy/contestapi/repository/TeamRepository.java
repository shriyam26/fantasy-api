package com.fantasy.contestapi.repository;

import com.fantasy.contestapi.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    List<Team> findByIdIn(List<String> id);
}