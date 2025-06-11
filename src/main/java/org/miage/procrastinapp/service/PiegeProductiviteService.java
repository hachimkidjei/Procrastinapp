package org.miage.procrastinapp.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.miage.procrastinapp.entity.PiegeProductivite;
import org.miage.procrastinapp.entity.Utilisateur;
import org.miage.procrastinapp.entity.PiegeProductivite.StatutPiege;
import org.miage.procrastinapp.exception.PiegeNotFoundException;
import org.miage.procrastinapp.exception.UtilisateurNotFoundException;
import org.miage.procrastinapp.repository.PiegeProductiviteRepository;
import org.miage.procrastinapp.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PiegeProductiviteService {

    private final PiegeProductiviteRepository piegeRepo;
    private final UtilisateurRepository utilisateurRepo;

    public PiegeProductivite creerPiege(Long createurId, PiegeProductivite piege) {
        Utilisateur createur = utilisateurRepo.findById(createurId)
                .orElseThrow(() -> new UtilisateurNotFoundException("Créateur introuvable avec l'id : " + createurId));

        if (createur.getRole() != Utilisateur.Role.REPENTI) {
            throw new IllegalArgumentException("Seuls les utilisateurs de rôle REPENTI peuvent créer des pièges.");
        }

        piege.setCreateur(createur);
        piege.setDateCreation(LocalDateTime.now());
        piege.setStatut(StatutPiege.ACTIF); // statut par défaut

        return piegeRepo.save(piege);
    }

    public PiegeProductivite modifierPiege(Long piegeId, PiegeProductivite updated) {
        PiegeProductivite existant = piegeRepo.findById(piegeId)
                .orElseThrow(() -> new PiegeNotFoundException("Piège non trouvé avec l'id : " + piegeId));

        existant.setTitre(updated.getTitre());
        existant.setDescription(updated.getDescription());
        existant.setType(updated.getType());
        existant.setNiveauDifficulte(updated.getNiveauDifficulte());
        existant.setRecompenseSiSucces(updated.getRecompenseSiSucces());
        existant.setConsequenceSiEchec(updated.getConsequenceSiEchec());
        existant.setStatut(updated.getStatut());

        return piegeRepo.save(existant);
    }

    public void supprimerPiege(Long piegeId) {
        PiegeProductivite piege = piegeRepo.findById(piegeId)
                .orElseThrow(() -> new PiegeNotFoundException("Piège introuvable avec id : " + piegeId));
        piegeRepo.delete(piege);
    }

    public List<PiegeProductivite> getAll() {
        return piegeRepo.findAll();
    }

    public PiegeProductivite getById(Long id) {
        return piegeRepo.findById(id)
                .orElseThrow(() -> new PiegeNotFoundException("Piège introuvable avec id : " + id));
    }

    public List<PiegeProductivite> getPiegesParCreateur(Long createurId) {
        Utilisateur utilisateur = utilisateurRepo.findById(createurId)
                .orElseThrow(() -> new UtilisateurNotFoundException("Utilisateur non trouvé avec l'id : " + createurId));
        return piegeRepo.findByCreateur(utilisateur);
    }

    public List<PiegeProductivite> getPiegesActifs() {
        return piegeRepo.findByStatut(StatutPiege.ACTIF);
    }
}
