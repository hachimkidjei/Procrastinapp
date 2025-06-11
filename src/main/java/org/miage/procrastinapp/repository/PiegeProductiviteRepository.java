package org.miage.procrastinapp.repository;

import org.miage.procrastinapp.entity.PiegeProductivite;
import org.miage.procrastinapp.entity.Utilisateur;
import org.miage.procrastinapp.entity.PiegeProductivite.StatutPiege;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PiegeProductiviteRepository extends JpaRepository<PiegeProductivite, Long> {
    List<PiegeProductivite> findByCreateur(Utilisateur createur);
    List<PiegeProductivite> findByStatut(StatutPiege statut);
}
