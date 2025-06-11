package org.miage.procrastinapp.repository;

import org.miage.procrastinapp.entity.ConfrontationPiege;
import org.miage.procrastinapp.entity.PiegeProductivite;
import org.miage.procrastinapp.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConfrontationPiegeRepository extends JpaRepository<ConfrontationPiege, Long> {

    List<ConfrontationPiege> findByUtilisateur(Utilisateur utilisateur);

    List<ConfrontationPiege> findByPiege(PiegeProductivite piege);

    List<ConfrontationPiege> findByUtilisateurAndResultat(Utilisateur utilisateur, ConfrontationPiege.Resultat resultat);
}
