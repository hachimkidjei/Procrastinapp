package org.miage.procrastinapp.service;

import lombok.RequiredArgsConstructor;
import org.miage.procrastinapp.dto.AttributionRecompenseDTO;
import org.miage.procrastinapp.entity.AttributionRecompense;
import org.miage.procrastinapp.entity.Recompense;
import org.miage.procrastinapp.entity.Utilisateur;
import org.miage.procrastinapp.exception.UtilisateurNotFoundException;
import org.miage.procrastinapp.repository.AttributionRecompenseRepository;
import org.miage.procrastinapp.repository.RecompenseRepository;
import org.miage.procrastinapp.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttributionRecompenseService {

    private final AttributionRecompenseRepository repository;
    private final UtilisateurRepository utilisateurRepo;
    private final RecompenseRepository recompenseRepo;

    public AttributionRecompense creerAttribution(AttributionRecompenseDTO dto) {
        Utilisateur utilisateur = utilisateurRepo.findById(dto.getUtilisateurId())
                .orElseThrow(() -> new UtilisateurNotFoundException("Utilisateur introuvable"));
        Recompense recompense = recompenseRepo.findById(dto.getRecompenseId())
                .orElseThrow(() -> new RuntimeException("Récompense introuvable"));

        AttributionRecompense ar = new AttributionRecompense();
        ar.setUtilisateur(utilisateur);
        ar.setRecompense(recompense);
        ar.setNom(dto.getNom());
        ar.setDescription(dto.getDescription());
        ar.setPointsBonus(dto.getPointsBonus());
        ar.setDateAttribution(dto.getDateAttribution() != null ? dto.getDateAttribution() : LocalDate.now());

        return repository.save(ar);
    }

    public List<AttributionRecompense> getByUtilisateur(Long utilisateurId) {
        Utilisateur utilisateur = utilisateurRepo.findById(utilisateurId)
                .orElseThrow(() -> new UtilisateurNotFoundException("Utilisateur introuvable"));
        return repository.findByUtilisateur(utilisateur);
    }
}
