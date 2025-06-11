package org.miage.procrastinapp.repository;

import org.miage.procrastinapp.entity.AttributionRecompense;
import org.miage.procrastinapp.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttributionRecompenseRepository extends JpaRepository<AttributionRecompense, Long> {
    List<AttributionRecompense> findByUtilisateur(Utilisateur utilisateur);
}
