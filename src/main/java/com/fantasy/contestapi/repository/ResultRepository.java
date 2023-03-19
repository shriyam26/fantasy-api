package com.fantasy.contestapi.repository;

import com.fantasy.contestapi.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {

    List<Result> findByPlayerIdIn(List<Long> playerIds);
}
