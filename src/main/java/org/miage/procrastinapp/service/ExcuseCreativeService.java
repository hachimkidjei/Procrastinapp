package org.miage.procrastinapp.service;

import lombok.RequiredArgsConstructor;
import org.miage.procrastinapp.entity.ExcuseCreative;
import org.miage.procrastinapp.entity.Utilisateur;
import org.miage.procrastinapp.exception.ExcuseNotFoundException;
import org.miage.procrastinapp.exception.UtilisateurNotFoundException;
import org.miage.procrastinapp.repository.ExcuseCreativeRepository;
import org.miage.procrastinapp.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExcuseCreativeService {

    private final ExcuseCreativeRepository excuseRepo;
    private final UtilisateurRepository utilisateurRepo;

    public ExcuseCreative soumettreExcuse(Long auteurId, ExcuseCreative excuse) {
        Utilisateur auteur = utilisateurRepo.findById(auteurId)
                .orElseThrow(() -> new UtilisateurNotFoundException("Auteur introuvable"));

        excuse.setAuteur(auteur);
        excuse.setStatut(ExcuseCreative.Statut.EN_ATTENTE);
        excuse.setVotes(0);
        excuse.setDateSoumission(LocalDate.now());

        return excuseRepo.save(excuse);
    }

    public ExcuseCreative approuverExcuse(Long id) {
        ExcuseCreative excuse = excuseRepo.findById(id)
                .orElseThrow(() -> new ExcuseNotFoundException("Excuse non trouvée"));
        excuse.setStatut(ExcuseCreative.Statut.APPROUVEE);
        return excuseRepo.save(excuse);
    }

    public ExcuseCreative rejeterExcuse(Long id) {
        ExcuseCreative excuse = excuseRepo.findById(id)
                .orElseThrow(() -> new ExcuseNotFoundException("Excuse non trouvée"));
        excuse.setStatut(ExcuseCreative.Statut.REJETEE);
        return excuseRepo.save(excuse);
    }

    public ExcuseCreative voterPourExcuse(Long id) {
        ExcuseCreative excuse = excuseRepo.findById(id)
                .orElseThrow(() -> new ExcuseNotFoundException("Excuse non trouvée"));
        excuse.setVotes(excuse.getVotes() + 1);
        return excuseRepo.save(excuse);
    }

    public List<ExcuseCreative> getExcusesApprouvees() {
        return excuseRepo.findByStatut(ExcuseCreative.Statut.APPROUVEE);
    }
}
