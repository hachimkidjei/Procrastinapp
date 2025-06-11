package org.miage.procrastinapp.service;

import lombok.RequiredArgsConstructor;
import org.miage.procrastinapp.entity.TacheAEviter;
import org.miage.procrastinapp.entity.Utilisateur;
import org.miage.procrastinapp.exception.TacheNotFoundException;
import org.miage.procrastinapp.exception.UtilisateurNotFoundException;
import org.miage.procrastinapp.repository.TacheAEviterRepository;
import org.miage.procrastinapp.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TacheAEviterService {

    private final TacheAEviterRepository tacheRepo;
    private final UtilisateurRepository utilisateurRepo;

    public TacheAEviter creerTache(Long utilisateurId, TacheAEviter tache) {
        Utilisateur utilisateur = utilisateurRepo.findById(utilisateurId)
                .orElseThrow(() -> new UtilisateurNotFoundException("Utilisateur introuvable"));

        tache.setUtilisateur(utilisateur);
        tache.setDateCreation(LocalDate.now());
        tache.setStatut(TacheAEviter.Statut.EN_ATTENTE);

        return tacheRepo.save(tache);
    }

    public List<TacheAEviter> listerTachesParUtilisateur(Long utilisateurId) {
        Utilisateur utilisateur = utilisateurRepo.findById(utilisateurId)
                .orElseThrow(() -> new UtilisateurNotFoundException("Utilisateur introuvable"));
        return tacheRepo.findByUtilisateur(utilisateur);
    }

    public TacheAEviter changerStatutTache(Long idTache, TacheAEviter.Statut nouveauStatut) {
        TacheAEviter tache = tacheRepo.findById(idTache)
                .orElseThrow(() -> new TacheNotFoundException("Tâche introuvable"));
        tache.setStatut(nouveauStatut);
        return tacheRepo.save(tache);
    }

    public void supprimerTache(Long idTache) {
        if (!tacheRepo.existsById(idTache)) {
            throw new TacheNotFoundException("Tâche à éviter non trouvée");
        }
        tacheRepo.deleteById(idTache);
    }
}
