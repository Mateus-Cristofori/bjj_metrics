package com.bjj_metrics_brasil.fight.repository;

import com.bjj_metrics_brasil.fight.repository.entity.Fight;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FightRepository extends JpaRepository<Fight, UUID> {}
