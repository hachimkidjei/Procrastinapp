package org.miage.procrastinapp.service;

import lombok.RequiredArgsConstructor;
import org.miage.procrastinapp.entity.DefiProcrastination;
import org.miage.procrastinapp.entity.Utilisateur;
import org.miage.procrastinapp.exception.DefiNotFoundException;
import org.miage.procrastinapp.exception.UtilisateurNotFoundException;
import org.miage.procrastinapp.repository.DefiProcrastinationRepository;
import org.miage.procrastinapp.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefiProcrastinationService {

    private final DefiProcrastinationRepository defiRepo;
    private final UtilisateurRepository utilisateurRepo;

    public DefiProcrastination creerDefi(DefiProcrastination defi) {
        defi.setStatut(DefiProcrastination.Statut.ACTIF);
        return defiRepo.save(defi);
    }

    public List<DefiProcrastination> getAll() {
        return defiRepo.findAll();
    }

    public DefiProcrastination getById(Long id) {
        return defiRepo.findById(id)
                .orElseThrow(() -> new DefiNotFoundException("Défi non trouvé avec l'id : " + id));
    }

    public void supprimerDefi(Long id) {
        DefiProcrastination defi = getById(id);
        defiRepo.delete(defi);
    }

    public List<DefiProcrastination> getDefisParCreateur(Long utilisateurId) {
        Utilisateur utilisateur = utilisateurRepo.findById(utilisateurId)
                .orElseThrow(() -> new UtilisateurNotFoundException("Utilisateur non trouvé avec id : " + utilisateurId));
        return defiRepo.findByCreateur(utilisateur);
    }
}
