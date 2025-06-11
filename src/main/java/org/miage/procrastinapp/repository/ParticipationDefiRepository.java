package org.miage.procrastinapp.repository;

import org.miage.procrastinapp.entity.ParticipationDefi;
import org.miage.procrastinapp.entity.Utilisateur;
import org.miage.procrastinapp.entity.DefiProcrastination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParticipationDefiRepository extends JpaRepository<ParticipationDefi, Long> {

    List<ParticipationDefi> findByUtilisateur(Utilisateur utilisateur);

    List<ParticipationDefi> findByDefi(DefiProcrastination defi);

    Optional<ParticipationDefi> findByUtilisateurAndDefi(Utilisateur utilisateur, DefiProcrastination defi);

    long countByUtilisateurIdAndStatutIn(Long utilisateurId, List<ParticipationDefi.StatutParticipation> statuts);

    long countByDefiIdAndStatutIn(Long defiId, List<ParticipationDefi.StatutParticipation> statuts);

    List<ParticipationDefi> findByUtilisateurId(Long utilisateurId);
}
