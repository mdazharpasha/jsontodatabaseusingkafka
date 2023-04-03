package com.example.demo.Repo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.example.demo.Binding.BettingData;
@Repository
@EnableJpaRepositories
public interface BettingDataRepository extends JpaRepository<BettingData,Serializable> {
@Query("select distinct (Game) from BettingData")
public List<String> getGame();

@Query("select distinct (stak) from BettingData")
public List<String> getStake();
}
