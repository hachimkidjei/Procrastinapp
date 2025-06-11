package org.miage.procrastinapp.service;

import lombok.RequiredArgsConstructor;
import org.miage.procrastinapp.entity.DefiProcrastination;
import org.miage.procrastinapp.entity.ParticipationDefi;
import org.miage.procrastinapp.entity.Utilisateur;
import org.miage.procrastinapp.exception.DefiNotFoundException;
import org.miage.procrastinapp.exception.ParticipationNonValideException;
import org.miage.procrastinapp.exception.UtilisateurNotFoundException;
import org.miage.procrastinapp.repository.DefiProcrastinationRepository;
import org.miage.procrastinapp.repository.ParticipationDefiRepository;
import org.miage.procrastinapp.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipationDefiService {

    private final ParticipationDefiRepository participationRepo;
    private final UtilisateurRepository utilisateurRepo;
    private final DefiProcrastinationRepository defiRepo;

    public ParticipationDefi inscrireUtilisateurADefi(Long utilisateurId, Long defiId) {
        Utilisateur utilisateur = utilisateurRepo.findById(utilisateurId)
                .orElseThrow(() -> new UtilisateurNotFoundException("Utilisateur introuvable"));

        DefiProcrastination defi = defiRepo.findById(defiId)
                .orElseThrow(() -> new DefiNotFoundException("Défi introuvable"));

        long nbParticipations = participationRepo.countByUtilisateurIdAndStatutIn(utilisateurId,
                List.of(ParticipationDefi.StatutParticipation.INSCRIT, ParticipationDefi.StatutParticipation.EN_COURS));

        if (nbParticipations >= 3) {
            throw new ParticipationNonValideException("Un utilisateur ne peut pas participer à plus de 3 défis simultanément");
        }

        long nbParticipantsDefi = participationRepo.countByDefiIdAndStatutIn(defiId,
                List.of(ParticipationDefi.StatutParticipation.INSCRIT, ParticipationDefi.StatutParticipation.EN_COURS));

        if (nbParticipantsDefi >= 5) {
            throw new ParticipationNonValideException("Un défi ne peut avoir plus de 5 participants simultanés");
        }

        ParticipationDefi participation = new ParticipationDefi();
        participation.setUtilisateur(utilisateur);
        participation.setDefi(defi);
        participation.setDateInscription(LocalDate.now());
        participation.setStatut(ParticipationDefi.StatutParticipation.INSCRIT);
        participation.setPointsGagnes(0);

        return participationRepo.save(participation);
    }

    public List<ParticipationDefi> getParticipationsParUtilisateur(Long utilisateurId) {
        return participationRepo.findByUtilisateurId(utilisateurId);
    }

    public List<ParticipationDefi> getParticipationsParDefi(Long defiId) {
        DefiProcrastination defi = defiRepo.findById(defiId)
                .orElseThrow(() -> new DefiNotFoundException("Défi introuvable"));
        return participationRepo.findByDefi(defi);
    }

    public List<ParticipationDefi> findAll() {
        return participationRepo.findAll();
    }

    public void terminerParticipation(Long idParticipation, int points) {
        ParticipationDefi participation = participationRepo.findById(idParticipation)
                .orElseThrow(() -> new ParticipationNonValideException("Participation non trouvée"));

        participation.setStatut(ParticipationDefi.StatutParticipation.TERMINE);
        participation.setPointsGagnes(points);
        participationRepo.save(participation);
    }
}
