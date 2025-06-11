package org.miage.procrastinapp.service;

import lombok.RequiredArgsConstructor;
import org.miage.procrastinapp.dto.ConfrontationPiegeDTO;
import org.miage.procrastinapp.entity.ConfrontationPiege;
import org.miage.procrastinapp.entity.PiegeProductivite;
import org.miage.procrastinapp.entity.Utilisateur;
import org.miage.procrastinapp.exception.ConfrontationNotFoundException;
import org.miage.procrastinapp.exception.PiegeNotFoundException;
import org.miage.procrastinapp.exception.UtilisateurNotFoundException;
import org.miage.procrastinapp.repository.ConfrontationPiegeRepository;
import org.miage.procrastinapp.repository.PiegeProductiviteRepository;
import org.miage.procrastinapp.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConfrontationPiegeService {

    private final ConfrontationPiegeRepository confrontationRepo;
    private final UtilisateurRepository utilisateurRepo;
    private final PiegeProductiviteRepository piegeRepo;

    /**
     * Enregistrer une nouvelle confrontation entre un utilisateur et un piège.
     */
    public ConfrontationPiege enregistrerConfrontation(ConfrontationPiegeDTO dto) {
        Utilisateur utilisateur = utilisateurRepo.findById(dto.getUtilisateurId())
                .orElseThrow(() -> new UtilisateurNotFoundException("Utilisateur non trouvé avec id : " + dto.getUtilisateurId()));

        PiegeProductivite piege = piegeRepo.findById(dto.getPiegeId())
                .orElseThrow(() -> new PiegeNotFoundException("Piège non trouvé avec id : " + dto.getPiegeId()));

        ConfrontationPiege confrontation = new ConfrontationPiege();
        confrontation.setUtilisateur(utilisateur);
        confrontation.setPiege(piege);
        confrontation.setDateConfrontation(dto.getDateConfrontation() != null ? dto.getDateConfrontation() : LocalDate.now());
        confrontation.setResultat(ConfrontationPiege.Resultat.valueOf(dto.getResultat().toUpperCase()));
        confrontation.setPoints(dto.getPoints());
        confrontation.setCommentaire(dto.getCommentaire());

        return confrontationRepo.save(confrontation);
    }

    /**
     * Récupérer toutes les confrontations d’un utilisateur.
     */
    public List<ConfrontationPiege> getConfrontationsParUtilisateur(Long utilisateurId) {
        Utilisateur utilisateur = utilisateurRepo.findById(utilisateurId)
                .orElseThrow(() -> new UtilisateurNotFoundException("Utilisateur introuvable avec id : " + utilisateurId));
        return confrontationRepo.findByUtilisateur(utilisateur);
    }

    /**
     * Récupérer toutes les confrontations liées à un piège.
     */
    public List<ConfrontationPiege> getConfrontationsParPiege(Long piegeId) {
        PiegeProductivite piege = piegeRepo.findById(piegeId)
                .orElseThrow(() -> new PiegeNotFoundException("Piège introuvable avec id : " + piegeId));
        return confrontationRepo.findByPiege(piege);
    }

    /**
     * Obtenir une confrontation précise par son ID.
     */
    public ConfrontationPiege getById(Long id) {
        return confrontationRepo.findById(id)
                .orElseThrow(() -> new ConfrontationNotFoundException("Confrontation non trouvée avec id : " + id));
    }
}
